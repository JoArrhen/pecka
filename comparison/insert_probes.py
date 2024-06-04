import sys, re, os

total = 0

def is_constructor(filename, method_string):
    classname = re.search(r"\/(\w+)\.java", filename).group(1)
    return method_string.startswith(classname + "(")

def probe_file(filename):
    global total
    with open(filename) as f:
        lines = f.readlines()
    
    with open("skipfile.txt") as f:
        skip = [s.strip() for s in f.readlines()]

    if filename in skip or "-info.java" in filename:
        print("Skipping", filename + ", in skip file")
        return "".join(lines)

    classname = re.search(r"\/(\w+)\.java", filename)
    if not classname:
        print(skip)
        print(filename)
    classname = re.search(r"\/(\w+)\.java", filename).group(1)
    if any(["interface " + classname in line for line in lines]):
        print("Skipping", filename + ", is interface")
        return "".join(lines)


    probed_source = ""
    to_print = []
    current_is_constructor = False

    for line in lines:
        #param_pattern = r"[A-Za-z0-9]+\(([A-Za-z0-9]+ [A-Za-z0-9_]+(, )?)+\)"
        param_line_pattern = r"[A-Za-z0-9]+\(([A-Za-z0-9\[\]\<\>]+ [A-Za-z0-9_]+[, ]*)+\)"
        if (param_line := re.search(param_line_pattern, line)) and not line.startswith("//") and '"' not in line and "abstract" not in line and "*" not in line:
            method_string = param_line.group(0)
            current_is_constructor = is_constructor(filename, method_string)
            #for param in re.finditer(r"([A-Za-z0-9\[\]\<\>]+ [A-Za-z0-9_]+)", method_string):
            for param in re.finditer(r"final ([A-Za-z0-9\[\]\<\>]+ [A-Za-z0-9_]+)", method_string):
                _, type_, name = param.group(0).split()
                if type_[0].isupper():
                    to_print.append(f'if ({name} != null) System.err.println("{type_};{name};{filename};{method_string};" + {name}.getClass().getSimpleName());\n')

        if not current_is_constructor and "{" in line:
            i = line.find("{") + 1
            probed_source += line[:i]
            for sout in to_print:
                probed_source += sout
                total += 1

            to_print = []
            probed_source += line[i:]
        elif current_is_constructor and "}" in line:
            i = line.find("}")
            probed_source += line[:i]
            for sout in to_print:
                probed_source += sout
                total += 1

            to_print = []
            probed_source += line[i:]
        else:
            probed_source += line

    return probed_source

for filename in sys.argv[1:]:
    new_path = "probed/" + filename
    os.makedirs(os.path.dirname(new_path), exist_ok=True)

    with open(new_path, "w") as f:
        f.write(probe_file(filename))

print(total, "probes inserted")


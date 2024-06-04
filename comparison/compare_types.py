import sys, json

if len(sys.argv) != 3:
    print("Usage: <json> <json>")
    sys.exit()

with open(sys.argv[1]) as f:
    truth = json.load(f)

with open(sys.argv[2]) as f:
    other = json.load(f)

def to_dict(js):
    res = {}
    for d in js:
        sig = d["method_signature"]
        for var, types in d["vars"].items():
            res[(sig, var)] = set(types)
    return res

truth = to_dict(truth)
other = to_dict(other)

misses = 0
total_types = 0
total_otypes = 0
found_count = 0
missed_count = 0
false_pos = 0
count = 0
missed_reachable_methods = set()

for mp, types in truth.items():
    try:
        otypes = other[mp]
        otypes -= {"Map"} # 
        types -= {"Map", "Null", ""}#, "String", "String[]", "Lookahead[]"}
        found_count += len(types & otypes)
        not_found_types = types - otypes
        missed_count += len(not_found_types)
        false_pos += len(otypes - types)
        total_types += len(types)
        total_otypes += len(otypes)

        if not_found_types:
            misses += 1
            print("Did not find types", not_found_types, "in", mp)
        count += 1

    except KeyError:
        missed_reachable_methods.add(mp[0])
        print("Missing key:", mp)

print("\n\n-----\n")
print(f"Incorrect variables: {misses}/{count}")
print("Correctly reported types:", found_count)
print("Overapproximated types:", false_pos)
print("Not reported types:", missed_count)
print(f"Recall: {found_count}/{total_types}={'{:.2f}'.format(found_count/total_types)}")
print(f"Precision: {found_count}/{total_otypes}={'{:.2f}'.format(found_count/(total_otypes))}")
correct_variables = count - misses
print(f"Parameters where all types were found: {correct_variables}/{count}={'{:.2f}'.format(correct_variables/count)}")

print("\n\n-----\n")
print("Missed reachable methods:", len(missed_reachable_methods))
for m in missed_reachable_methods:
    print(m)


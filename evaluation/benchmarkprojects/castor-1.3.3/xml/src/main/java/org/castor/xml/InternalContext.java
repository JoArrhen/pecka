/*
 * Copyright 2007 Joachim Grueneis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.castor.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.mapping.MappingLoader;
import org.exolab.castor.util.RegExpEvaluator;
import org.exolab.castor.xml.Introspector;
import org.exolab.castor.xml.NodeType;
import org.exolab.castor.xml.OutputFormat;
import org.exolab.castor.xml.ResolverException;
import org.exolab.castor.xml.Serializer;
import org.exolab.castor.xml.XMLClassDescriptorResolver;
import org.exolab.castor.xml.util.ResolverStrategy;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Parser;
import org.xml.sax.XMLReader;

/**
 * The internal context is meant as center piece providing (and keeping) all
 * information that is required by Marshaller, Unmarshaller, SourceGenerator,
 * MappingTool, SchemaReader and SchemaWriter. It is created, filled with
 * initial data and put into all other parts of Castor by {@link XMLContext}.
 * It is NOT meant to be directly instantiated by user implementations!
 * For all other objects it provides access to Castor state information
 * (e.g. known descriptors) and configuration values.
 * 
 * @author <a href="mailto:jgrueneis At gmail DOT com">Joachim Grueneis</a>
 * @since 1.1.2
 */
public interface InternalContext extends PropertyChangeProvider {

    /**
     * Instructs Castor to load class descriptors from the mapping given.
     * @param mapping Castor XML mapping (file), from which the required class
     * descriptors will be derived. 
     * @throws MappingException If the {@link Mapping} cannot be loaded and analyzed successfully.
     */
    public abstract void addMapping(final Mapping mapping) throws MappingException;

    /**
     * Loads the class descriptor for the class instance specified. The use of this method is useful
     * when no mapping is used, as happens when the domain classes has been generated
     * using the XML code generator (in which case instead of a mapping file class
     * descriptor files will be generated).
     * 
     * @param clazz the class for which the associated descriptor should be loaded.
     * @throws ResolverException in case that resolving the Class fails fatally
     */
    public abstract void addClass(final Class clazz) throws ResolverException;

    /**
     * Loads the class descriptor for the class instance specified. The use of this method is useful
     * when no mapping is used, as happens when the domain classes hase been generated
     * using the XML code generator (in which case instead of a mapping file class
     * descriptor files will be generated).
     * 
     * @param clazzes the classes for which the associated descriptor should be loaded.
     * @throws ResolverException in case that resolving the Class fails fatally
     */
    public abstract void addClasses(final Class[] clazzes) throws ResolverException;

    /**
     * Loads class descriptors from the package specified. The use of this method is useful
     * when no mapping is used, as happens when the domain classes hase been generated
     * using the XML code generator (in which case instead of a mapping file class
     * descriptor files will be generated).
     * <p>
     * Please note that this functionality will work only if you provide the <tt>.castor.cdr</tt>
     * file with your generated classes (as generated by the XML code generator).
     * <p>
     * @param packageName The package name for the (descriptor) classes
     * @throws ResolverException 
     *          If there's a problem loading class descriptors for the given package. 
     */
    public abstract void addPackage(final String packageName) throws ResolverException;

    /**
     * Loads class descriptors from the packages specified. The use of this method is useful
     * when no mapping is used, as happens when the domain classes hase been generated
     * using the XML code generator (in which case instead of a mapping file class
     * descriptor files will be generated).
     * <p>
     * Please note that this functionality will work only if you provide the <tt>.castor.cdr</tt>
     * files with your generated classes (as generated by the XML code generator).
     * <p>
     * @param packageNames The package names for the (descriptor) classes
     * @throws ResolverException 
     *          If there's a problem loading class descriptors for the given package. 
     */
    public abstract void addPackages(final String[] packageNames) throws ResolverException;

    /**
     * Sets an application-specific {@link XMLClassDescriptorResolver} instance.
     * @param xmlClassDescriptorResolver the resolver to use
     */
    public abstract void setResolver(final XMLClassDescriptorResolver xmlClassDescriptorResolver);

    /**
     * To set properties for marshalling and unmarshalling behavior. 
     * @param propertyName name of the property to set
     * @param value the value to set to
     */
    public abstract void setProperty(final String propertyName, final Object value);

    /**
     * To get the value of a specific property.
     * @param propertyName name of the Property
     * @return the value (Object) of the property
     */
    public abstract Object getProperty(final String propertyName);

    /**
     * Returns the naming conventions to use for the XML framework.
     *
     * @return the naming conventions to use for the XML framework     
     */
    public abstract XMLNaming getXMLNaming();

    /**
     * Returns the naming conventions to use for the XML framework.
     * @param classLoader the class loader to be used when instantiating a new naming instance
     * @return the naming conventions to use for the XML framework
     * @TODO: Joachim should be synchronized, shouldn't it be??
     */
    public abstract XMLNaming getXMLNaming(final ClassLoader classLoader); //-- getXMLNaming

    /**
     * The {@link JavaNaming} instance to be used.
     * @return {@link JavaNaming} instance to be used.
     */
    public abstract JavaNaming getJavaNaming();

    /**
     * Return an XML document parser implementing the feature list
     * specified in the configuration file.
     *
     * @return A suitable XML parser
     */
    public abstract Parser getParser();

    /**
     * Returns an XML document parser implementing the requested
     * set of features. The feature list is a comma separated list
     * of features that parser may or may not support. No errors are
     * generated for unsupported features. If the feature list is not
     * null, it overrides the default feature list specified in the
     * configuration file, including validation and Namespaces.
     *
     * @param features The requested feature list, null for the
     *   defaults
     * @return A suitable XML parser
     */
    public abstract Parser getParser(final String features);

    /**
     * Returns an XML document parser implementing the requested set of
     * features. The feature list is a comma separated list of features that
     * parser may or may not support. No errors are generated for unsupported
     * features. If the feature list is not null, it overrides the default
     * feature list specified in the configuration file, including validation
     * and Namespaces.
     * 
     * @return A suitable XML parser
     */
    public abstract XMLReader getXMLReader(); //-- getXMLReader

    /**
     * Returns an XML document parser implementing the requested
     * set of features. The feature list is a comma separated list
     * of features that parser may or may not support. No errors are
     * generated for unsupported features. If the feature list is not
     * null, it overrides the default feature list specified in the
     * configuration file, including validation and Namespaces.
     *
     * @param features the name of feature to set
     * @return A suitable XML parser
     */
    public abstract XMLReader getXMLReader(final String features); //-- getXMLReader

    /**
     * Returns the NodeType to use for Java primitives.
     * A null value will be returned if no NodeType was specified,
     * indicating the default NodeType should be used.
     *
     * @return the NodeType assigned to Java primitives, or null
     * if no NodeType was specified.
     **/
    public abstract NodeType getPrimitiveNodeType(); //-- getPrimitiveNodeType

    /**
     * Returns a new instance of the specified Regular Expression
     * Evaluator, or null if no validator was specified.
     *
     * @return the regular expression evaluator,
     *
     **/
    public abstract RegExpEvaluator getRegExpEvaluator(); // -- getRegExpEvaluator

    /**
     * Returns a default serializer for producing an XML document. The caller
     * can specify an alternative output format, may reuse this serializer
     * across several streams, and may serialize both DOM and SAX events. If
     * such control is not required, it is recommended to call one of the other
     * two methods.
     * 
     * @return A suitable serializer
     */
    public abstract Serializer getSerializer();

    /**
     * Returns the default OutputFormat for use with a Serializer.
     *
     * @return the default OutputFormat
     **/
    public abstract OutputFormat getOutputFormat(); //-- getOutputFormat

    /**
     * Returns a default serializer for producing an XML document to
     * the designated output stream using the default serialization
     * format.
     *
     * @param output The output stream
     * @return A suitable serializer
     * @throws IOException if instantiation of the serializer fails
     */
    public abstract DocumentHandler getSerializer(final OutputStream output) throws IOException;

    /**
     * Returns a default serializer for producing an XML document to the
     * designated output stream using the default serialization format.
     * 
     * @param output
     *            The output stream
     * @return A suitable serializer
     * @throws IOException if instantiation of serializer fails
     */
    public abstract DocumentHandler getSerializer(final Writer output) throws IOException;

    /**
     * To get the XMLClassdescriptorResolver instance hold in the context.
     * @return the XMLClassdescriptorResolver instance hold in the context
     */
    public abstract XMLClassDescriptorResolver getXMLClassDescriptorResolver();

    /**
     * To get the Introspector assigned to this XMLContext.
     * @return the Introspector assigned to this XMLContext
     */
    public abstract Introspector getIntrospector();

    /**
     * To get the XMLClassDescriptor resolver strategy to be used when
     * resolving classes into class descriptors.
     * @return the ResolverStrategy to use
     */
    public abstract ResolverStrategy getResolverStrategy();

    /**
     * To set the XMLClassDescriptor resolver strategy to be used.
     * @param resolverStrategy the ResolverStrategy to use
     */
    public abstract void setResolverStrategy(final ResolverStrategy resolverStrategy);

    /**
     * To set the {@link MappingLoader} to be used in this Castor session.
     * @param mappingLoader the {@link MappingLoader} to use
     */
    public abstract void setMappingLoader(final MappingLoader mappingLoader);

    /**
     * To get the {@link MappingLoader} specified to be used in this Castor session.
     * @return the {@link MappingLoader} to use
     */
    public abstract MappingLoader getMappingLoader();

    /**
     * To set the {@link JavaNaming}?property.
     * @param javaNaming the {@link JavaNaming} to use
     */
    public abstract void setJavaNaming(final JavaNaming javaNaming);

    /**
     * To set the {@link XMLNaming} property.
     * @param xmlNaming the {@link XMLNaming} to use
     */
    abstract void setXMLNaming(final XMLNaming xmlNaming);
    
    /**
     * To set any boolean property.
     * @param propertyName name of the property to set
     * @param value boolean value to set
     */
    public abstract void setProperty(final String propertyName, final boolean value);

    /**
     * Providing access to Boolean properties of the configuration.
     * @param propertyName name of the property
     * @return null if property is not set or whichever value is set
     */
    public abstract Boolean getBooleanProperty(final String propertyName);

    /**
     * Providing access to String properties of the configuration.
     * @param propertyName name of the property
     * @return null if the property is not set or whichever value is set
     */
    public abstract String getStringProperty(final String propertyName);

    /**
     * To set the class loader to be used in all further marshalling, unmarshalling
     * and other actions.
     * @param classLoader the ClassLoader instance to use
     */
    public abstract void setClassLoader(final ClassLoader classLoader);

    /**
     * To set the {@link XMLClassDescriptorResolver} to be used. Be aware, that the
     * XMLClassDescriptorResolver instance holds a descriptor cache!! Maybe change it
     * to have the descriptor cache as part of the context?
     * @param xmlClassDescriptorResolver the {@link XMLClassDescriptorResolver} to use
     */
    public abstract void setXMLClassDescriptorResolver(
            final XMLClassDescriptorResolver xmlClassDescriptorResolver);

    /**
     * To specify which {@link Introspector}?is to be used.
     * @param introspector {@link Introspector} to be used
     */
    public abstract void setIntrospector(final Introspector introspector);

    /**
     * To get the ClassLoader to use for loading resources.
     * @return the ClassLoader to use
     */
    public abstract ClassLoader getClassLoader();

    /**
     * Get lenient id validation flag.
     * @return lenient id validation flag
     */
    public abstract boolean getLenientIdValidation();

    /**
     * Get lenient sequence order flag.
     * @return lenient sequence order flag
     */
    public abstract boolean getLenientSequenceOrder();

    /**
     * Get load package mapping flag.
     * @return load package mapping flag
     */
    public abstract Boolean getLoadPackageMapping();

    /**
     * To set the load package mapping flag.
     * @param loadPackageMapping the load package mapping flag
     */
    public abstract void setLoadPackageMapping(final Boolean loadPackageMapping);

    /**
     * To get use-introspection flag.
     * @return use-introspection flag
     */
    public abstract Boolean getUseIntrospector();

    /**
     * To set use-introspection flag.
     * @param useIntrospector use-introspection flag
     */
    public abstract void setUseIntrospector(final Boolean useIntrospector);

    /**
     * To get marshalling-validation flag.
     * @return marshalling-validation flag
     */
    public abstract boolean marshallingValidation();

    /**
     * To get strict-element flag.
     * @return strict-element flag
     */
    public abstract boolean strictElements();

}
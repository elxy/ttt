/*
 * Copyright 2013-2019 Skynav, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY SKYNAV, INC. AND ITS CONTRIBUTORS “AS IS” AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL SKYNAV, INC. OR ITS CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.skynav.ttv.model.ttml;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import com.skynav.ttv.model.AbstractModel;
import com.skynav.ttv.model.Profile;
import com.skynav.ttv.model.ttml1.tt.Body;
import com.skynav.ttv.model.ttml1.tt.Break;
import com.skynav.ttv.model.ttml1.tt.Division;
import com.skynav.ttv.model.ttml1.tt.Head;
import com.skynav.ttv.model.ttml1.tt.Layout;
import com.skynav.ttv.model.ttml1.tt.Metadata;
import com.skynav.ttv.model.ttml1.tt.Paragraph;
import com.skynav.ttv.model.ttml1.tt.Region;
import com.skynav.ttv.model.ttml1.tt.Span;
import com.skynav.ttv.model.ttml1.tt.Style;
import com.skynav.ttv.model.ttml1.tt.Styling;
import com.skynav.ttv.model.ttml1.tt.TimedText;
import com.skynav.ttv.model.ttml1.ttm.Agent;
import com.skynav.ttv.util.Annotations;
import com.skynav.ttv.verifier.MetadataVerifier;
import com.skynav.ttv.verifier.ParameterVerifier;
import com.skynav.ttv.verifier.ProfileVerifier;
import com.skynav.ttv.verifier.SemanticsVerifier;
import com.skynav.ttv.verifier.StyleVerifier;
import com.skynav.ttv.verifier.TimingVerifier;
import com.skynav.ttv.verifier.ttml.TTML1MetadataVerifier;
import com.skynav.ttv.verifier.ttml.TTML1ParameterVerifier;
import com.skynav.ttv.verifier.ttml.TTML1ProfileVerifier;
import com.skynav.ttv.verifier.ttml.TTML1SemanticsVerifier;
import com.skynav.ttv.verifier.ttml.TTML1StyleVerifier;
import com.skynav.ttv.verifier.ttml.TTML1TimingVerifier;
import com.skynav.xml.helpers.XML;


public class TTML1 {

    public static class Constants extends TTML.Constants {

        public static final String XSD_TTML1 = "com/skynav/ttv/xsd/ttml1/ttml1.xsd";

        public static final String PROFILE_TTML1_PRESENTATION = "dfxp-presentation";
        public static final String PROFILE_TTML1_TRANSFORMATION = "dfxp-transformation";
        public static final String PROFILE_TTML1_FULL = "dfxp-full";

        public static final String PROFILE_TTML1_PRESENTATION_ABSOLUTE = NAMESPACE_TT_PROFILE + PROFILE_TTML1_PRESENTATION;
        public static final String PROFILE_TTML1_TRANSFORMATION_ABSOLUTE = NAMESPACE_TT_PROFILE + PROFILE_TTML1_TRANSFORMATION;
        public static final String PROFILE_TTML1_FULL_ABSOLUTE = NAMESPACE_TT_PROFILE + PROFILE_TTML1_FULL;

        public static final String ATTR_AGENT = "agent";
        public static final String ATTR_REGION = "region";
        public static final String ATTR_STYLE = "style";

    }

    public static final String MODEL_NAME = "ttml1";
    public static final int MODEL_VERSION = 1;

    public static class TTML1Model extends AbstractModel {

        public static final QName agentElementName = new com.skynav.ttv.model.ttml1.ttm.ObjectFactory().createAgent(new Agent()).getName();
        public static final QName bodyElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createBody(new Body()).getName();
        public static final QName breakElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createBr(new Break()).getName();
        public static final QName divisionElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createDiv(new Division()).getName();
        public static final QName headElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createHead(new Head()).getName();
        public static final QName layoutElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createLayout(new Layout()).getName();
        public static final QName metadataElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createMetadata(new Metadata()).getName();
        public static final QName paragraphElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createP(new Paragraph()).getName();
        public static final QName regionElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createRegion(new Region()).getName();
        public static final QName spanElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createSpan(new Span()).getName();
        public static final QName styleElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createStyle(new Style()).getName();
        public static final QName stylingElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createStyling(new Styling()).getName();
        public static final QName timedTextElementName = new com.skynav.ttv.model.ttml1.tt.ObjectFactory().createTt(new TimedText()).getName();

        private static final Class<?>[] profileSpecificationConstructorParameterTypes = new Class<?>[] { URI.class };

        private String[] schemaResourceNames;
        private URI[] namespaceURIs;
        private URI profileNamespaceUri;
        private URI featureNamespaceUri;
        private URI extensionNamespaceUri;
        private Map<String,String> normalizedPrefixes1;
        private Map<URI,Class<?>> profileSpecificationClasses;
        private Map<URI,Profile.Specification> profileSpecifications;
        private Profile.StandardDesignations standardDesignations;
        private List<QName> idAttributes;
        private Map<Class<?>,String> rootClasses;
        private SemanticsVerifier semanticsVerifier;
        private ParameterVerifier parameterVerifier;
        private ProfileVerifier profileVerifier;
        private StyleVerifier styleVerifier;
        private TimingVerifier timingVerifier;
        private MetadataVerifier metadataVerifier;

        public TTML1Model() {
            populate();
        }

        private void populate() {
            populateSchemaResourceNames();
            populateNamespaceURIs();
        }

        private void populateSchemaResourceNames() {
            List<String> resourceNames = new java.util.ArrayList<String>();
            resourceNames.add(Constants.XSD_TTML1);
            this.schemaResourceNames = resourceNames.toArray(new String[resourceNames.size()]);
        }

        private void populateNamespaceURIs() {
            List<URI> namespaceURIs = new java.util.ArrayList<URI>();
            try {
                namespaceURIs.add(new URI(Constants.NAMESPACE_TT));
                namespaceURIs.add(new URI(Constants.NAMESPACE_TT_METADATA));
                namespaceURIs.add(new URI(Constants.NAMESPACE_TT_PARAMETER));
                namespaceURIs.add(new URI(Constants.NAMESPACE_TT_STYLE));
                namespaceURIs.add(new URI(Constants.NAMESPACE_TTT_EXTENSIONS));
                this.namespaceURIs = namespaceURIs.toArray(new URI[namespaceURIs.size()]);
                this.profileNamespaceUri = new URI(Constants.NAMESPACE_TT_PROFILE);
                this.featureNamespaceUri = new URI(Constants.NAMESPACE_TT_FEATURE);
                this.extensionNamespaceUri = new URI(Constants.NAMESPACE_TT_EXTENSION);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        public String getName() {
            return MODEL_NAME;
        }

        public String[] getNameAliases() {
            return new String[0];
        }

        public int getTTMLVersion() {
            return MODEL_VERSION;
        }

        public String[] getTTSchemaResourceNames() {
            return schemaResourceNames;
        }

        public String[] getSchemaResourceNames() {
            return getTTSchemaResourceNames();
        }

        public URI[] getTTNamespaceURIs() {
            return namespaceURIs;
        }

        public URI[] getNamespaceURIs() {
            return getTTNamespaceURIs();
        }

        public final URI getTTProfileNamespaceUri() {
            return profileNamespaceUri;
        }

        public URI getProfileNamespaceUri() {
            return getTTProfileNamespaceUri();
        }

        public final URI getTTFeatureNamespaceUri() {
            return featureNamespaceUri;
        }

        public URI getFeatureNamespaceUri() {
            return getTTFeatureNamespaceUri();
        }

        public final URI getTTExtensionNamespaceUri() {
            return extensionNamespaceUri;
        }

        public URI getExtensionNamespaceUri() {
            return getTTExtensionNamespaceUri();
        }

        public Map<String,String> getNormalizedPrefixes() {
            if (normalizedPrefixes1 == null) {
                normalizedPrefixes1 = new java.util.HashMap<String,String>();
                normalizedPrefixes1.put(XML.xmlNamespace, "xml");
                normalizedPrefixes1.put(XML.xmlnsNamespace, "xmlns");
                normalizedPrefixes1.put(XML.xsiNamespace, "xsi");
                normalizedPrefixes1.put(Constants.NAMESPACE_TT, "");
                normalizedPrefixes1.put(Constants.NAMESPACE_TT_METADATA, "ttm");
                normalizedPrefixes1.put(Constants.NAMESPACE_TT_PARAMETER, "ttp");
                normalizedPrefixes1.put(Constants.NAMESPACE_TT_STYLE, "tts");
                normalizedPrefixes1.put(Constants.NAMESPACE_TT_ISD, "isd");
                normalizedPrefixes1.put(Constants.NAMESPACE_TTT_EXTENSIONS, "ttt");
                normalizedPrefixes1.put(Annotations.getNamespace(), Annotations.getNamespacePrefix());
            }
            return normalizedPrefixes1;
        }

        protected Map<URI,Class<?>> getProfileSpecificationClasses() {
            if (profileSpecificationClasses == null) {
                profileSpecificationClasses = new java.util.HashMap<URI,Class<?>>();
                profileSpecificationClasses.put(profileNamespaceUri.resolve(Constants.PROFILE_TTML1_TRANSFORMATION), TTML1TransformationProfileSpecification.class);
                profileSpecificationClasses.put(profileNamespaceUri.resolve(Constants.PROFILE_TTML1_PRESENTATION), TTML1PresentationProfileSpecification.class);
                profileSpecificationClasses.put(profileNamespaceUri.resolve(Constants.PROFILE_TTML1_FULL), TTML1FullProfileSpecification.class);
            }
            return profileSpecificationClasses;
        }

        public Set<URI> getProfileDesignators() {
            return getProfileSpecificationClasses().keySet();
        }

        public Profile.Specification getProfileSpecification(URI uri) {
            if ((profileSpecifications != null) && profileSpecifications.containsKey(uri))
                return profileSpecifications.get(uri);
            else if (!getProfileSpecificationClasses().containsKey(uri))
                return null;
            else {
                Profile.Specification ps = createProfileSpecification(getProfileSpecificationClasses().get(uri), uri);
                if (profileSpecifications == null)
                    profileSpecifications = new java.util.HashMap<URI,Profile.Specification>();
                profileSpecifications.put(uri, ps);
                return ps;
            }
        }

        protected Profile.Specification createProfileSpecification(Class<?> psc, URI uri) {
            try {
                Object[] parameters = new Object[] { uri };
                return (Profile.Specification) psc.getDeclaredConstructor(profileSpecificationConstructorParameterTypes).newInstance(parameters);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public Profile.StandardDesignations getStandardDesignations() {
            if (standardDesignations == null)
                standardDesignations = TTML1StandardDesignations.getInstance();
            return standardDesignations;
        }

        public boolean isStandardFeatureDesignation(URI uri) {
            return getStandardDesignations().isStandardFeatureDesignation(uri);
        }

        public boolean isStandardExtensionDesignation(URI uri) {
            return getStandardDesignations().isStandardExtensionDesignation(uri);
        }

        public String getJAXBContextPath() {
            return "com.skynav.ttv.model.ttml1.tt:com.skynav.ttv.model.ttml1.ttm:com.skynav.ttv.model.ttml1.ttp";
        }

        public List<QName> getIdAttributes() {
            if (idAttributes == null) {
                idAttributes = new java.util.ArrayList<QName>();
                idAttributes.add(XML.getIdAttributeName());
            }
            return idAttributes;
        }

        public Map<Class<?>,String> getRootClasses() {
            if (rootClasses == null) {
                rootClasses = new java.util.HashMap<Class<?>,String>();
                rootClasses.put(com.skynav.ttv.model.ttml1.tt.TimedText.class, "createTt");
                rootClasses.put(com.skynav.ttv.model.ttml1.ttp.Profile.class, "createProfile");
            }
            return rootClasses;
        }

        public QName getIdReferenceTargetName(QName attributeName) {
            String namespaceUri = attributeName.getNamespaceURI();
            String localName = attributeName.getLocalPart();
            if (isEmptyNamespace(namespaceUri)) {
                if (localName.equals(Constants.ATTR_AGENT))
                    return agentElementName;
                else if (localName.equals(Constants.ATTR_REGION))
                    return regionElementName;
                else if (localName.equals(Constants.ATTR_STYLE))
                    return styleElementName;
            } else if (namespaceUri.equals(Constants.NAMESPACE_TT_METADATA)) {
                if (localName.equals(Constants.ATTR_AGENT))
                    return agentElementName;
            }
            return null;
        }

        public Class<?> getIdReferenceTargetClass(QName attributeName) {
            String namespaceUri = attributeName.getNamespaceURI();
            String localName = attributeName.getLocalPart();
            if (isEmptyNamespace(namespaceUri)) {
                if (localName.equals(Constants.ATTR_AGENT))
                    return Agent.class;
                else if (localName.equals(Constants.ATTR_REGION))
                    return Region.class;
                else if (localName.equals(Constants.ATTR_STYLE))
                    return Style.class;
            } else if (namespaceUri.equals(Constants.NAMESPACE_TT_METADATA)) {
                if (localName.equals(Constants.ATTR_AGENT))
                    return Agent.class;
            }
            return Object.class;
        }

        public List<List<QName>> getIdReferencePermissibleAncestors(QName attributeName) {
            List<List<QName>> permissibleAncestors = new java.util.ArrayList<List<QName>>();
            String namespaceUri = attributeName.getNamespaceURI();
            String localName = attributeName.getLocalPart();
            if (localName.equals(Constants.ATTR_STYLE) && isEmptyNamespace(namespaceUri)) {
                List<QName> ancestors = new java.util.ArrayList<QName>();
                ancestors.add(stylingElementName);
                ancestors.add(headElementName);
                permissibleAncestors.add(ancestors);
            } else if (localName.equals(Constants.ATTR_REGION) && isEmptyNamespace(namespaceUri)) {
                List<QName> ancestors = new java.util.ArrayList<QName>();
                ancestors.add(layoutElementName);
                ancestors.add(headElementName);
                permissibleAncestors.add(ancestors);
            } else if (localName.equals(Constants.ATTR_AGENT) && (isEmptyNamespace(namespaceUri) || namespaceUri.equals(Constants.NAMESPACE_TT_METADATA))) {
                List<QName> ancestors1 = new java.util.ArrayList<QName>();
                ancestors1.add(metadataElementName);
                ancestors1.add(headElementName);
                permissibleAncestors.add(ancestors1);
                List<QName> ancestors2 = new java.util.ArrayList<QName>();
                ancestors2.add(headElementName);
                permissibleAncestors.add(ancestors2);
            }
            return (permissibleAncestors.size() > 0) ? permissibleAncestors : null;
        }

        public static boolean isEmptyNamespace(String namespaceUri) {
            return (namespaceUri == null) || (namespaceUri.length() == 0);
        }

        static public boolean isTTElement(QName name) {
            return name.equals(timedTextElementName);
        }

        static public boolean isTTBodyElement(QName name) {
            return name.equals(bodyElementName);
        }

        static public boolean isTTDivElement(QName name) {
            return name.equals(divisionElementName);
        }

        static public boolean isTTParagraphElement(QName name) {
            return name.equals(paragraphElementName);
        }

        static public boolean isTTSpanElement(QName name) {
            return name.equals(spanElementName);
        }

        static public boolean isTTBreakElement(QName name) {
            return name.equals(breakElementName);
        }

        static public boolean isTTContentElement(QName name) {
            if (isTTBodyElement(name))
                return true;
            else if (isTTDivElement(name))
                return true;
            else if (isTTParagraphElement(name))
                return true;
            else if (isTTSpanElement(name))
                return true;
            else if (isTTBreakElement(name))
                return true;
            else
                return false;
        }

        static public boolean isTTStyleElement(QName name) {
            return name.equals(styleElementName);
        }

        static public boolean isTTRegionElement(QName name) {
            return name.equals(regionElementName);
        }

        static public boolean isTTContentOrRegionElement(QName name) {
            if (isTTContentElement(name))
                return true;
            else if (isTTRegionElement(name))
                return true;
            else
                return false;
        }

        public Collection<QName> getDefinedStyleNames() {
            return getStyleVerifier().getDefinedStyleNames();
        }

        public Collection<QName> getApplicableStyleNames(QName eltName) {
            return getStyleVerifier().getApplicableStyleNames(eltName);
        }

        public boolean isInheritableStyle(QName eltName, QName styleName) {
            return getStyleVerifier().isInheritableStyle(eltName, styleName);
        }

        public String getInitialStyleValue(QName eltName, QName styleName) {
            return getStyleVerifier().getInitialStyleValue(eltName, styleName);
        }

        public boolean doesStyleApply(QName eltName, QName styleName) {
            return getStyleVerifier().doesStyleApply(eltName, styleName);
        }

        public boolean isNegativeLengthPermitted(QName eltName, QName styleName) {
            return getStyleVerifier().isNegativeLengthPermitted(eltName, styleName);
        }

        public SemanticsVerifier getSemanticsVerifier() {
            if (semanticsVerifier == null) {
                semanticsVerifier = (SemanticsVerifier) new TTML1SemanticsVerifier(this);
            }
            return semanticsVerifier;
        }

        public ParameterVerifier getParameterVerifier() {
            if (parameterVerifier == null) {
                parameterVerifier = new TTML1ParameterVerifier(this);
            }
            return parameterVerifier;
        }

        public ProfileVerifier getProfileVerifier() {
            if (profileVerifier == null) {
                profileVerifier = new TTML1ProfileVerifier(this);
            }
            return profileVerifier;
        }

        public StyleVerifier getStyleVerifier() {
            if (styleVerifier == null) {
                styleVerifier = new TTML1StyleVerifier(this);
            }
            return styleVerifier;
        }

        public TimingVerifier getTimingVerifier() {
            if (timingVerifier == null) {
                timingVerifier = new TTML1TimingVerifier(this);
            }
            return timingVerifier;
        }

        public MetadataVerifier getMetadataVerifier() {
            if (metadataVerifier == null) {
                metadataVerifier = new TTML1MetadataVerifier(this);
            }
            return metadataVerifier;
        }

    }

}

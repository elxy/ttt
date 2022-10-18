/*
 * Copyright 2014 Skynav, Inc. All rights reserved.
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

package com.skynav.ttv.util;

import javax.xml.namespace.QName;

public class StyleSpecification implements Comparable<StyleSpecification> {

    private ComparableQName name;
    private String value;
    private boolean inherited;

    public StyleSpecification(String nsUri, String localName, String value, boolean inherited) {
        this(new ComparableQName(nsUri, localName), value, inherited);
    }

    public StyleSpecification(QName name, String value, boolean inherited) {
        this(new ComparableQName(name), value, inherited);
    }

    public StyleSpecification(ComparableQName name, String value, boolean inherited) {
        assert name != null;
        assert value != null;
        this.name = name;
        this.value = value;
        this.inherited = inherited;
    }

    public ComparableQName getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int compareTo(StyleSpecification other) {
        int d = name.compareTo(other.name);
        d = (d != 0) ? d : value.compareTo(other.value);
        return (d != 0) ? d : (inherited ? 1 : 0) - (other.inherited ? 1 : 0);
    }

    @Override
    public int hashCode() {
        int hc = 23;
        hc = hc * 31 + name.hashCode();
        hc = hc * 31 + value.hashCode();
        hc = hc * 2 + (inherited ? 1 : 0);
        return hc;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof StyleSpecification) {
            StyleSpecification other = (StyleSpecification) o;
            if (!name.equals(other.name))
                return false;
            else if (!value.equals(other.value))
                return false;
            else
                return inherited == other.inherited;
        } else
            return false;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        sb.append(name);
        sb.append(',');
        sb.append(value);
        if (inherited)
            sb.append(",inherited");
        sb.append(']');
        return sb.toString();
    }

    public boolean isInherited() {
        return inherited;
    }

}

/*
 *  This file is part of Thiesen Collections.
 *  
 *  Copyright (c) 2010 by Marcus Thiesen (marcus@thiesen.org)
 *
 *  Thiesen Collections is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Thiesen Collections is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Thiesen Collections.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.thiesen.collections.set.impl;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

public class MutableEnumSet<E extends Enum<E>> extends AbstractDelegatingMutableSet<E> implements Serializable {

    private static final long serialVersionUID = -7864965638395369744L;
    private final EnumSet<E> _enumSet;

    private MutableEnumSet( final EnumSet<E> set ) {
        super( set );
        _enumSet = set;
    }
    
    public static <T extends Enum<T>> MutableEnumSet<T> allOf( final Class<T> elementType ) {
        return new MutableEnumSet<T>( EnumSet.allOf( elementType ) );
    }

    public static <E extends Enum<E>> MutableEnumSet<E> complementOf( final MutableEnumSet<E> s ) {
        return new MutableEnumSet<E>( EnumSet.<E>complementOf( s._enumSet ) );
    }
    
    @Override
    public Set<E> copyToMutableCollections() {
        return _enumSet.clone();
    }
    
}

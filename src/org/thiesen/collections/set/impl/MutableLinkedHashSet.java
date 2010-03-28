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

import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.collect.Sets;

public class MutableLinkedHashSet<E> extends AbstractDelegatingMutableSet<E> {

    private final LinkedHashSet<E> _linkdedHashSet;

    private MutableLinkedHashSet( final LinkedHashSet<E> set ) {
        super( set );
        _linkdedHashSet = set;
    }
    
    public static <T> MutableLinkedHashSet<T> create() {
        return new MutableLinkedHashSet<T>( Sets.<T>newLinkedHashSet() );
    }
    
    public static <T> MutableLinkedHashSet<T> copyOf( final Iterable<T> elements ) {
        return new MutableLinkedHashSet<T>( Sets.<T>newLinkedHashSet( elements ) );
    }
    
    @SuppressWarnings( "unchecked" )
    @Override
    public Set<E> copyToMutableCollections() {
        return (Set<E>) _linkdedHashSet.clone();
    }

}

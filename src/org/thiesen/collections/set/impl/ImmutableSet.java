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

import java.util.Set;

import org.thiesen.collections.set.IMutableSet;


public class ImmutableSet<E> 
    extends AbstractDelegatingImmutableSet<E> {
    
    private final Set<E> _immutableSet;

    private ImmutableSet( final com.google.common.collect.ImmutableSet<E> set ) {
        super( set );
        _immutableSet = set;
    }
    
    public static <T> ImmutableSet<T> copyOf( final Iterable<T> elements ) {
        return new ImmutableSet<T>( com.google.common.collect.ImmutableSet.copyOf( elements ) );
    }

    public static <T> ImmutableSet<T> of( final T... elements ) {
        return new ImmutableSet<T>( com.google.common.collect.ImmutableSet.of( elements ) );
    }

    @Override
    public IMutableSet<E> mutableCopy() {
        return MutableHashSet.copyOf( _immutableSet );
    }
    
}

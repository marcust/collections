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

import java.util.Comparator;


public class ImmutableSortedSet<E> 
    extends AbstractDelegatingImmutableSet<E> {
    
    private ImmutableSortedSet( final com.google.common.collect.ImmutableSet<E> set ) {
        super( set );        
    }
    
    public static <T> ImmutableSortedSet<T> copyOf( final Iterable<T> elements ) {
        return new ImmutableSortedSet<T>( com.google.common.collect.ImmutableSortedSet.copyOf( elements ) );
    }

    public static <T> ImmutableSortedSet<T> copyOf( final Comparator<? super T> comparator, final Iterable<T> elements ) {
        return new ImmutableSortedSet<T>( com.google.common.collect.ImmutableSortedSet.copyOf( comparator, elements ) );
    }


    
}

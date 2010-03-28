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
import java.util.Set;
import java.util.TreeSet;

import com.google.common.collect.Sets;

public class MutableTreeSet<E> extends AbstractDelegatingMutableSet<E> {

    private final TreeSet<E> _treeSet;

    private MutableTreeSet( final TreeSet<E> set ) {
        super( set );
        _treeSet = set;
    }
    
    public static <T extends Comparable<T>> MutableTreeSet<T> create() {
        return new MutableTreeSet<T>( Sets.<T>newTreeSet() );
    }
    
    public static <T extends Comparable<T>> MutableTreeSet<T> withComparator( final Comparator<? super T> comparator ) {
        return new MutableTreeSet<T>( Sets.<T>newTreeSet( comparator ) );
    }
    
    public static <T extends Comparable<T>> MutableTreeSet<T> copyOf( final Iterable<T> elements ) {
        return new MutableTreeSet<T>( Sets.<T>newTreeSet( elements ) );
    }
    
    
    @SuppressWarnings( "unchecked" )
    @Override
    public Set<E> copyToMutableCollections() {
        return (Set<E>) _treeSet.clone();
    }

}

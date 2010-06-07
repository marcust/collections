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
import java.util.Comparator;

import org.thiesen.collections.common.view.set.ImmutableSortedSetView;
import org.thiesen.collections.set.IImmutableSortedSet;
import org.thiesen.collections.set.IMutableSortedSet;
import org.thiesen.collections.set.views.IImmutableSortedSetView;


public class ImmutableSortedSet<E> 
    extends AbstractDelegatingImmutableSet<E> 
    implements IImmutableSortedSet<E>, Serializable {

    private static final long serialVersionUID = 4270687233661901815L;
    private final com.google.common.collect.ImmutableSortedSet<E> _sortedSet;

    private ImmutableSortedSet( final com.google.common.collect.ImmutableSortedSet<E> set ) {
        super( set );        
        _sortedSet = set;
    }
    
    public static <T> ImmutableSortedSet<T> copyOf( final Iterable<T> elements ) {
        if ( elements instanceof ImmutableSortedSet<?> ) {
            return (ImmutableSortedSet<T>) elements;
        }
        
        return new ImmutableSortedSet<T>( com.google.common.collect.ImmutableSortedSet.copyOf( elements ) );
    }

    public static <T> ImmutableSortedSet<T> copyOf( final Comparator<? super T> comparator, final Iterable<T> elements ) {
        return new ImmutableSortedSet<T>( com.google.common.collect.ImmutableSortedSet.copyOf( comparator, elements ) );
    }

    @Override
    public IMutableSortedSet<E> mutableCopy() {
        return MutableTreeSet.<E>copyOf( _sortedSet.comparator(), _sortedSet );
    }

    @Override
    public IImmutableSortedSetView<E> headSet( final E toElement ) {
        return SetViews.asIImmutableSortedSetView( _sortedSet.headSet( toElement ) );
    }

    @Override
    public IImmutableSortedSetView<E> subSet( final E fromElement, final E toElement ) {
        return SetViews.asIImmutableSortedSetView( _sortedSet.subSet( fromElement, toElement ) );
    }

    @Override
    public IImmutableSortedSetView<E> tailSet( final E fromElement ) {
        return SetViews.asIImmutableSortedSetView( _sortedSet.tailSet( fromElement ) );
    }

    @Override
    public Comparator<? super E> comparator() {
        return _sortedSet.comparator();
    }

    @Override
    public E first() {
        return _sortedSet.first();
    }

    @Override
    public E last() {
        return _sortedSet.last();
    }

    @Override
    public ImmutableSortedSetView<E> asCollectionsView() {
        return SetViews.asImmutableSortedSetView( _sortedSet );
    }
}

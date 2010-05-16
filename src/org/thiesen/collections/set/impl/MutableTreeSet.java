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
import java.util.Set;
import java.util.TreeSet;

import org.thiesen.collections.common.view.set.MutableSortedSetView;
import org.thiesen.collections.set.IImmutableSortedSet;
import org.thiesen.collections.set.IMutableSortedSet;
import org.thiesen.collections.set.ISortedSet;
import org.thiesen.collections.set.views.IMutableSortedSetView;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class MutableTreeSet<E> 
    extends AbstractDelegatingMutableSet<E> 
    implements IMutableSortedSet<E>, Serializable {

    private static final long serialVersionUID = -1451034411874694744L;
    
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
    
    public static <T> MutableTreeSet<T> copyOf( final ISortedSet<T> elements ) {
        return new MutableTreeSet<T>( new TreeSet<T>( elements.asCollectionsView() ) );
    }
    
    
    public static <T> MutableTreeSet<T> copyOf(
            final Comparator<? super T> comparator,
            final Iterable<T> elements ) {
        final TreeSet<T> newTreeSet = Sets.<T>newTreeSet( comparator );
        Iterables.addAll( newTreeSet, elements );
        return new MutableTreeSet<T>( newTreeSet );
    }
    
    
    @SuppressWarnings( "unchecked" )
    @Override
    public Set<E> copyToMutableCollections() {
        return (Set<E>) _treeSet.clone();
    }

    @Override
    public IImmutableSortedSet<E> immutableCopy() {
        return ImmutableSortedSet.copyOf( _treeSet.comparator(), _treeSet );
    }

    @Override
    public Comparator<? super E> comparator() {
        return _treeSet.comparator();
    }

    @Override
    public E first() {
        return _treeSet.first();
    }

    @Override
    public IMutableSortedSetView<E> headSet( final E toElement ) {
        return SetViews.asIMutableSortedSetView( _treeSet.headSet( toElement ) );
    }

    @Override
    public E last() {
        return _treeSet.last();
    }

    @Override
    public IMutableSortedSetView<E> subSet( final E fromElement, final E toElement ) {
        return SetViews.asIMutableSortedSetView( _treeSet.subSet( fromElement, toElement ) );
    }

    @Override
    public IMutableSortedSetView<E> tailSet( final E fromElement ) {
        return SetViews.asIMutableSortedSetView( _treeSet.tailSet( fromElement ) );
    }

    @Override
    public MutableSortedSetView<E> asCollectionsView() {
        return SetViews.asMutableSortedSetView( _treeSet );
    }
}

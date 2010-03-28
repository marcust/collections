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
package org.thiesen.collections.list.impl;

import java.util.Iterator;
import java.util.List;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.collection.IMutableCollectionView;
import org.thiesen.collections.common.MutableListView;
import org.thiesen.collections.list.IMutableList;
import org.thiesen.collections.list.views.IMutableListView;
import org.thiesen.collections.list.views.IUnmodifiableListView;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public abstract class AbstractDelegatingMutableIList<E> implements IMutableList<E> {

    private final List<E> _delegate;

    public AbstractDelegatingMutableIList( final List<E> delegate ) {
        _delegate = delegate;
    }

    public boolean add( final E e ) {
        return _delegate.add( e );
    }

    public void add( final int index, final E element ) {
        _delegate.add( index, element );
    }

    public boolean addAll( final ICollection<? extends E> c ) {
        return _delegate.addAll( c.copyToMutableCollections() );
    }

    public boolean addAll( final int index, final ICollection<? extends E> c ) {
        return _delegate.addAll( index, c.copyToMutableCollections() );
    }

    public void clear() {
        _delegate.clear();
    }

    public boolean contains( final Object o ) {
        return _delegate.contains( o );
    }

    public boolean containsAll( final ICollection<?> c ) {
        return _delegate.containsAll( c.copyToMutableCollections() );
    }

    public E get( final int index ) {
        return _delegate.get( index );
    }

    public int indexOf( final Object o ) {
        return _delegate.indexOf( o );
    }

    public boolean isEmpty() {
        return _delegate.isEmpty();
    }

    public Iterator<E> iterator() {
        return _delegate.iterator();
    }

    public int lastIndexOf( final Object o ) {
        return _delegate.lastIndexOf( o );
    }

    public E remove( final int index ) {
        return _delegate.remove( index );
    }

    public boolean remove( final Object o ) {
        return _delegate.remove( o );
    }

    public boolean removeAll( final ICollection<?> c ) {
        return _delegate.removeAll( c.copyToMutableCollections() );
    }

    public boolean retainAll( final ICollection<?> c ) {
        return _delegate.retainAll( c.copyToMutableCollections() );
    }

    public E set( final int index, final E element ) {
        return _delegate.set( index, element );
    }

    public int size() {
        return _delegate.size();
    }

    public Object[] toArray() {
        return _delegate.toArray();
    }

    @Override
    public IMutableListView<E> subList( final int fromIndex, final int toIndex ) {
        return ListViews.asIMutableListView( _delegate.subList( fromIndex, toIndex ) );
    }

    @Override
    public <T> IUnmodifiableListView<T> transform( final Function<E, T> transformFunction ) {
        return ListViews.asIUnmodifiableListView( Lists.transform( _delegate, transformFunction ) );
    }

    @Override
    public IUnmodifiableListView<E> asUnmodifiableView() {
        return ListViews.asIUnmodifiableListView( _delegate );
    }

    @Override
    public MutableListView<E> asCollectionsView() {
        return ListViews.asMutableListView( _delegate );
    }

    @Override
    public <T> T[] toArray( final T[] a ) {
        return _delegate.toArray( a );
    }

    @Override
    public IMutableCollectionView<E> filter( final Predicate<E> predicate ) {
        return CollectionViews.asMutableCollectionView( Collections2.filter(  _delegate, predicate ) );
    }

    @Override
    public ImmutableList<E> immutableCopy() {
        return ImmutableList.copyOf( _delegate );
    }


    
    
    
}

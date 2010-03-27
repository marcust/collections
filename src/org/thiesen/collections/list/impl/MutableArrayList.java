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

import java.util.ArrayList;
import java.util.Iterator;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.collection.IMutableCollectionView;
import org.thiesen.collections.common.MutableListView;
import org.thiesen.collections.list.IMutableListView;
import org.thiesen.collections.list.IMutableRandomAccessList;
import org.thiesen.collections.list.IUnmodifiableListView;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class MutableArrayList<E> implements IMutableRandomAccessList<E>, Iterable<E> {
 
    private final ArrayList<E> _arrayList;

    private MutableArrayList( final ArrayList<E> arrayList ) {
        _arrayList = arrayList;
    }

    public static <T> MutableArrayList<T> withInitialCapacity( final int capacity ) {
        return new MutableArrayList<T>( new ArrayList<T>( capacity ) );
    }
    
    public static <T> MutableArrayList<T> copyOf( final ICollection<? extends T> values ) {
        return new MutableArrayList<T>( new ArrayList<T>( values.asCollectionsView() ) );
    }
    
    public boolean add( final E e ) {
        return _arrayList.add( e );
    }

    public void add( final int index, final E element ) {
        _arrayList.add( index, element );
    }

    public boolean addAll( final ICollection<? extends E> c ) {
        return _arrayList.addAll( c.copyToCollections() );
    }

    public boolean addAll( final int index, final ICollection<? extends E> c ) {
        return _arrayList.addAll( index, c.copyToCollections() );
    }

    public void clear() {
        _arrayList.clear();
    }

    public boolean contains( final Object o ) {
        return _arrayList.contains( o );
    }

    public boolean containsAll( final ICollection<?> c ) {
        return _arrayList.containsAll( c.copyToCollections() );
    }

    public E get( final int index ) {
        return _arrayList.get( index );
    }

    public int indexOf( final Object o ) {
        return _arrayList.indexOf( o );
    }

    public boolean isEmpty() {
        return _arrayList.isEmpty();
    }

    public Iterator<E> iterator() {
        return _arrayList.iterator();
    }

    public int lastIndexOf( final Object o ) {
        return _arrayList.lastIndexOf( o );
    }

    public E remove( final int index ) {
        return _arrayList.remove( index );
    }

    public boolean remove( final Object o ) {
        return _arrayList.remove( o );
    }

    public boolean removeAll( final ICollection<?> c ) {
        return _arrayList.removeAll( c.copyToCollections() );
    }

    public boolean retainAll( final ICollection<?> c ) {
        return _arrayList.retainAll( c.copyToCollections() );
    }

    public E set( final int index, final E element ) {
        return _arrayList.set( index, element );
    }

    public int size() {
        return _arrayList.size();
    }

    public Object[] toArray() {
        return _arrayList.toArray();
    }

    @Override
    public IMutableListView<E> subList( final int fromIndex, final int toIndex ) {
        return ListViews.asIMutableListView( _arrayList.subList( fromIndex, toIndex ) );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public java.util.List<E> copyToCollections() {
        return (java.util.List<E>) _arrayList.clone();
    }

    @Override
    public <T> IUnmodifiableListView<T> transform( final Function<E, T> transformFunction ) {
        return ListViews.asUnmodifiableListView( Lists.transform( _arrayList, transformFunction ) );
    }

    @Override
    public MutableListView<E> asCollectionsView() {
        return ListViews.asMutableListView( _arrayList );
    }

    @Override
    public <T> T[] toArray( final T[] a ) {
        return _arrayList.toArray( a );
    }

    @Override
    public IMutableCollectionView<E> filter( final Predicate<E> predicate ) {
        return CollectionViews.asMutableCollectionView( Collections2.filter(  _arrayList, predicate ) );
    }
    
    
}

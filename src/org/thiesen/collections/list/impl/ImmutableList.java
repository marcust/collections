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

import java.util.List;

import org.thiesen.collections.common.iterator.ImmutableIterator;
import org.thiesen.collections.common.iterator.ImmutableIteratorImpl;
import org.thiesen.collections.common.view.list.ImmutableListView;
import org.thiesen.collections.list.IImmutableRandomAccessList;
import org.thiesen.collections.list.IMutableList;
import org.thiesen.collections.list.views.IUnmodifiableListView;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ImmutableList<E>
    extends ListViews.AbstractIList<E>
    implements 
    IImmutableRandomAccessList<E> {

    private final com.google.common.collect.ImmutableList<E> _list;
    
    private ImmutableList( final com.google.common.collect.ImmutableList<E> list ) {
        _list = list;
    }
    
    public static <T> ImmutableList<T> copyOf( final Iterable<T> iterable ) {
        return new ImmutableList<T>( com.google.common.collect.ImmutableList.copyOf( iterable ) );
    }
    
    @Override
    public IMutableList<E> mutableCopy() {
        return MutableArrayList.copyOf( _list );
    }

    @Override
    public ImmutableListView<E> asCollectionsView() {
        return ListViews.asImmutableListView( _list );
    }

    @Override
    public List<E> copyToMutableCollections() {
        return Lists.newArrayList( _list );
    }

    @Override
    public int indexOf( final Object o ) {
        return _list.indexOf( o );
    }

    @Override
    public int lastIndexOf( final Object o ) {
        return _list.lastIndexOf( o );
    }

    @Override
    public <T> IUnmodifiableListView<T> transform( final Function<E, T> transformFunction ) {
        return ListViews.asIUnmodifiableListView( Lists.transform( _list, transformFunction ) );
    }

    @Override
    public boolean contains( final Object o ) {
        return _list.contains( o );
    }

    @Override
    public boolean isEmpty() {
        return _list.isEmpty();
    }

    @Override
    public int size() {
        return _list.size();
    }

    @Override
    public Object[] toArray() {
        return _list.toArray();
    }

    @Override
    public <T> T[] toArray( final T[] a ) {
        return _list.toArray( a );
    }

    @Override
    public ImmutableIterator<E> iterator() {
        return ImmutableIteratorImpl.wrap( _list.iterator() );
    }

    @Override
    public E get( final int index ) {
        return _list.get( index );
        
    }

    @Override
    protected List<E> delegate() {
        return _list;
    }
    
}

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

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.pcollections.ConsPStack;
import org.pcollections.PStack;
import org.thiesen.collections.common.iterator.ImmutableIterator;
import org.thiesen.collections.common.iterator.ImmutableIteratorImpl;
import org.thiesen.collections.common.view.list.ImmutableListView;
import org.thiesen.collections.list.IImmutableRandomAccessList;
import org.thiesen.collections.list.IMutableList;
import org.thiesen.collections.list.views.IUnmodifiableListView;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ImmutablePList<E>
    extends ListViews.AbstractIList<E>
    implements 
    IImmutableRandomAccessList<E>,
    Serializable {

    private static final long serialVersionUID = -105090485056808607L;
    
    private final ConsPStack<E> _list;
    
    private ImmutablePList( final PStack<E> list ) {
        _list = ConsPStack.<E>empty().plusAll(  list );
    }
    
    public static <T> ImmutablePList<T> wrap( final PStack<T> existingStack ) {
        return new ImmutablePList<T>( existingStack );
    }
    
    public static <T> ImmutablePList<T> copyOf( final Iterable<T> iterable ) {
        if ( iterable instanceof ImmutablePList<?> ) {
            return (ImmutablePList<T>) iterable;
        }
        if ( iterable instanceof PStack<?> ) {
            return wrap( (PStack<T>)iterable );
        }
        
        return new ImmutablePList<T>( ConsPStack.<T>empty().plusAll(  com.google.common.collect.ImmutableList.copyOf( iterable ) ) );
    }
    
    public static <T> ImmutablePList<T> of( final T... elements ) {
        return copyOf( Arrays.asList( elements ) );
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
    protected PStack<E> delegate() {
        return _list;
    }

    @Override
    public ImmutablePList<E> append( final E value ) {
        return wrap( _list.plus( value ) );
    }
    
}

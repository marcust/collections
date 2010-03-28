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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.collection.IMutableCollectionView;
import org.thiesen.collections.common.ImmutableListView;
import org.thiesen.collections.common.ListView;
import org.thiesen.collections.common.MutableListView;
import org.thiesen.collections.common.UnmodifiableIteratorImpl;
import org.thiesen.collections.list.IImmutableListView;
import org.thiesen.collections.list.IMutableListView;
import org.thiesen.collections.list.IUnmodifiableListView;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;

public class ListViews {

    private static class ImmutableListViewImpl<E> extends ForwardingList<E> implements ImmutableListView<E> {

        private final List<E> _delegate;

        public ImmutableListViewImpl( final List<E> list ) {
            _delegate = list;
        }

        @Override
        protected List<E> delegate() {
            return _delegate;
        }

        @Override
        public void add( @SuppressWarnings( "unused" ) final int index, @SuppressWarnings( "unused" ) final E element ) {
            throw new UnsupportedOperationException("Immutable List View");
            
        }

        @Override
        public boolean addAll( @SuppressWarnings( "unused" ) final int index, @SuppressWarnings( "unused" ) final Collection<? extends E> elements ) {
            throw new UnsupportedOperationException("Immutable List View");
            
        }

        @Override
        public E set( @SuppressWarnings( "unused" ) final int index, @SuppressWarnings( "unused" ) final E element ) {
            throw new UnsupportedOperationException("Immutable List View");
            
        }

        @Override
        public List<E> subList( @SuppressWarnings( "unused" ) final int fromIndex, @SuppressWarnings( "unused" ) final int toIndex ) {
            throw new UnsupportedOperationException("Immutable List View");
            
        }
                
        
    }

    private static class IImmutableListViewImpl<E> implements IImmutableListView<E> {

        private final List<E> _list;

        private IImmutableListViewImpl( final List<E> list ) {
            _list = list;
            
        }

        @Override
        public ImmutableListView<E> asCollectionsView() {
            return new ImmutableListViewImpl<E>( _list );
        }

        @Override
        public boolean contains( final Object o ) {
            return _list.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _list.containsAll( c.asCollectionsView() );
        }

        @Override
        public List<E> copyToMutableCollections() {
            return Lists.newArrayList( _list );
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
        public <T> IImmutableListView<T> transform( final Function<E, T> transformFunction ) {
            return new IImmutableListViewImpl<T>( Lists.transform( _list, transformFunction ) );
            
        }

        @Override
        public Iterator<E> iterator() {
            return _list.iterator();
        }

        @Override
        public int indexOf( final Object o ) {
            return _list.indexOf( o );
        }

        @Override
        public int lastIndexOf( final Object o ) {
            return _list.lastIndexOf( o );
        }


    }

    private static class IMutableListViewImpl<E> implements IMutableListView<E> {

        private final List<E> _list;

        public IMutableListViewImpl( final List<E> list ) {
            _list = list;
        }

        @Override
        public void add( final int index, final E element ) {
            _list.add( index, element );
        }

        @Override
        public boolean addAll( final int index, final ICollection<? extends E> c ) {
            return _list.addAll( index, c.asCollectionsView() );
        }

        @Override
        public E remove( final int index ) {
            return _list.remove( index );
        }

        @Override
        public IMutableListView<E> subList( final int fromIndex, final int toIndex ) {
            return new IMutableListViewImpl<E>( _list.subList( fromIndex, toIndex ) );
            
        }

        @Override
        public MutableListView<E> asCollectionsView() {
            return new MutableListViewImpl<E>( _list );
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
            return new IUnmodifiableListViewImpl<T>( Lists.transform( _list, transformFunction ) );
        }

        @Override
        public boolean contains( final Object o ) {
            return _list.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _list.containsAll( c.asCollectionsView() );
        }

        @Override
        public IMutableCollectionView<E> filter( final Predicate<E> predicate ) {
            return new CollectionViews.IMutableCollectionViewImpl<E>( Collections2.filter( _list, predicate ) );
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
        public Iterator<E> iterator() {
            return _list.iterator();
        }

        @Override
        public boolean add( final E e ) {
            return _list.add( e );
        }

        @Override
        public boolean addAll( final ICollection<? extends E> c ) {
            return _list.addAll( c.asCollectionsView() );
        }

        @Override
        public void clear() {
            _list.clear();
        }

        @Override
        public boolean remove( final E o ) {
            return _list.remove( o );
        }

        @Override
        public boolean removeAll( final ICollection<?> c ) {
            return _list.removeAll( c.asCollectionsView() );
        }

        @Override
        public boolean retainAll( final ICollection<?> c ) {
            return _list.retainAll( c.asCollectionsView() );
        }

        @Override
        public ImmutableList<E> immutableCopy() {
            return ImmutableList.copyOf( this );
        }
    
        
    }

    private static class IUnmodifiableListViewImpl<E>
        implements IUnmodifiableListView<E> {

        private final List<E> _list;

        protected IUnmodifiableListViewImpl( final List<E> list ) {
            _list = list;
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
        public ListView<E> asCollectionsView() {
            return new MutableListViewImpl<E>( _list );
        }

        @Override
        public <T> IUnmodifiableListView<T> transform( final Function<E, T> transformFunction ) {
            return new IUnmodifiableListViewImpl<T>( Lists.transform( _list, transformFunction ) );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _list.containsAll( c.asCollectionsView() );
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
        public UnmodifiableIterator<E> iterator() {
            return UnmodifiableIteratorImpl.<E>wrap( _list.iterator() );
        }
        

    }

    private static class UnmodifiableListViewImpl<E> 
        implements IUnmodifiableListView<E> {

        public List<E> _list;

        public UnmodifiableListViewImpl( final List<E> list ) {
            _list = list;
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
        public ListView<E> asCollectionsView() {
            return new MutableListViewImpl<E>( _list );
        }

        @Override
        public boolean contains( final Object o ) {
            return _list.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _list.containsAll( c.asCollectionsView() );
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
        public <T> IUnmodifiableListView<T> transform( final Function<E, T> transformFunction ) {
            return new IUnmodifiableListViewImpl<T>( Lists.transform( _list, transformFunction ) );
            
        }

        @Override
        public UnmodifiableIterator<E> iterator() {
            return UnmodifiableIteratorImpl.wrap( _list.iterator() );
        }
                
    }

    private static class MutableListViewImpl<E> 
        extends ForwardingList<E>
        implements MutableListView<E> {

        private final List<E> _list;

        protected MutableListViewImpl( final List<E> list ) {
          
            _list = list;
        }

        @Override
        protected List<E> delegate() {
            return _list;
        }

    }
    
    public static <E> MutableListView<E> asMutableListView( final List<E> list ) {
        return new MutableListViewImpl<E>( list );
    }
    
    public static <E> IUnmodifiableListView<E> asIUnmodifiableListView( final List<E> list ) {
        return new UnmodifiableListViewImpl<E>( list );
    }
    
    
    public static <E> IMutableListView<E> asIMutableListView( final List<E> list ) {
        return new IMutableListViewImpl<E>( list );
    }
    
    public static <E> IImmutableListView<E> asIImmutableListView( final List<E> list ) {
        return new IImmutableListViewImpl<E>( list );
    }
    
    public static <E> ImmutableListView<E> asImmutableListView( final List<E> list ) {
        return new ImmutableListViewImpl<E>( list );
    }
}

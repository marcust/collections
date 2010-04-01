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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.collection.impl.CollectionViews;
import org.thiesen.collections.collection.impl.Collections3;
import org.thiesen.collections.collection.views.IMutableCollectionView;
import org.thiesen.collections.common.iterator.UnmodifiableIteratorImpl;
import org.thiesen.collections.common.view.list.ImmutableListView;
import org.thiesen.collections.common.view.list.MutableListView;
import org.thiesen.collections.common.view.list.UnmodifiableListView;
import org.thiesen.collections.list.IList;
import org.thiesen.collections.list.IMutableList;
import org.thiesen.collections.list.views.IImmutableListView;
import org.thiesen.collections.list.views.IMutableListView;
import org.thiesen.collections.list.views.IUnmodifiableListView;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;

public class ListViews {

    private static class UnmodifiableListViewImpl<E>
        extends ForwardingList<E>
        implements UnmodifiableListView<E> {

        private final List<E> _delegate;

        public UnmodifiableListViewImpl( final List<E> delegate ) {
            _delegate = Collections.unmodifiableList( delegate );
        }

        @Override
        protected List<E> delegate() {
            return _delegate;
        }

    }

    static abstract class AbstractIList<E> implements IList<E> {
        
        protected abstract List<E> delegate();
        

        @Override
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( delegate(), c );
        }
        
    }
    
    static abstract class AbstractIMutableList<E>
        extends AbstractIList<E> implements IMutableList<E> {
        
        @Override
        public boolean removeAll( final Iterable<?> i ) {
            return Collections3.removeAll( delegate(), i );
        }
        
        @Override
        public boolean retainAll( final Iterable<?> i ) {
            return Collections3.retainAll( delegate(), i );
        }
        
        @Override
        public boolean addAll( final Iterable<? extends E> i ) {
            return Iterables.addAll( delegate(), i );
        }
        
    }
    
    private static class ImmutableListViewImpl<E> 
        extends ForwardingList<E> 
        implements ImmutableListView<E> {

        private final List<E> _delegate;

        private ImmutableListViewImpl( final List<E> list ) {
            _delegate = Collections.unmodifiableList( list );
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

    private static class IImmutableListViewImpl<E>
        extends AbstractIList<E>
        implements IImmutableListView<E> {

        private final List<E> _list;

        private IImmutableListViewImpl( final List<E> list ) {
            _list = Collections.unmodifiableList( list );
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

        @Override
        protected List<E> delegate() {
            return _list;
        }


    }

    private static class IMutableListViewImpl<E>
        extends AbstractIMutableList<E>
        implements IMutableListView<E> {

        private final List<E> _list;

        private IMutableListViewImpl( final List<E> list ) {
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
            return CollectionViews.asMutableCollectionView( Collections2.filter( _list, predicate ) );
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
        public void clear() {
            _list.clear();
        }

        @Override
        public boolean remove( final Object o ) {
            return _list.remove( o );
        }

        @Override
        public ImmutableList<E> immutableCopy() {
            return ImmutableList.copyOf( this );
        }

        @Override
        public IUnmodifiableListView<E> asUnmodifiableView() {
            return new IUnmodifiableListViewImpl<E>( _list );
        }

        @Override
        protected List<E> delegate() {
            return _list;
        }


    }

    private static class IUnmodifiableListViewImpl<E>
        extends AbstractIList<E>
        implements IUnmodifiableListView<E> {

        private final List<E> _list;

        private IUnmodifiableListViewImpl( final List<E> list ) {
            _list = Collections.unmodifiableList( list );
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
        public UnmodifiableListView<E> asCollectionsView() {
            return new UnmodifiableListViewImpl<E>( _list );
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

        @Override
        protected List<E> delegate() {
            return _list;
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
    
    static <E> MutableListView<E> asMutableListView( final List<E> list ) {
        return new MutableListViewImpl<E>( list );
    }
    
    public static <E> IUnmodifiableListView<E> asIUnmodifiableListView( final List<E> list ) {
        return new IUnmodifiableListViewImpl<E>( list );
    }
    
    public static <E> UnmodifiableListView<E> asUnmodifiableListView( final List<E> list ) {
        return new UnmodifiableListViewImpl<E>( list );
    }
    
    static <E> IMutableListView<E> asIMutableListView( final List<E> list ) {
        return new IMutableListViewImpl<E>( list );
    }
    
    static <E> IImmutableListView<E> asIImmutableListView( final List<E> list ) {
        return new IImmutableListViewImpl<E>( list );
    }
    
    static <E> ImmutableListView<E> asImmutableListView( final List<E> list ) {
        return new ImmutableListViewImpl<E>( list );
    }
}

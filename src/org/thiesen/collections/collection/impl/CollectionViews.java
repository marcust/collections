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
package org.thiesen.collections.collection.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.collection.IMutableCollection;
import org.thiesen.collections.collection.views.IImmutableCollectionView;
import org.thiesen.collections.collection.views.IMutableCollectionView;
import org.thiesen.collections.collection.views.IUnmodifiableCollectionView;
import org.thiesen.collections.common.iterator.ImmutableIterator;
import org.thiesen.collections.common.iterator.ImmutableIteratorImpl;
import org.thiesen.collections.common.iterator.UnmodifiableIteratorImpl;
import org.thiesen.collections.common.view.collection.ImmutableCollectionView;
import org.thiesen.collections.common.view.collection.MutableCollectionView;
import org.thiesen.collections.common.view.collection.UnmodifiableCollectionView;
import org.thiesen.collections.list.impl.MutableArrayList;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;


public class CollectionViews {
    
    private static class IUnmodifiableCollectionViewImpl<E> 
        implements IUnmodifiableCollectionView<E> {

        private final Collection<E> _delegate;

        public IUnmodifiableCollectionViewImpl( final Collection<E> collection ) {
            _delegate = Collections.unmodifiableCollection( collection );
        }

        @Override
        public UnmodifiableCollectionView<E> asCollectionsView() {
            return new UnmodifiableCollectionViewImpl<E>( this );
        }

        @Override
        public boolean contains( final Object o ) {
            return _delegate.contains( o );
        }

        @Override
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( _delegate, c );
        }

        @Override
        public Collection<E> copyToMutableCollections() {
            return Lists.newArrayList( _delegate );
        }

        @Override
        public boolean isEmpty() {
            return _delegate.isEmpty();
        }
        
        @Override
        public boolean isNotEmpty() {
            return !isEmpty();
        }

        @Override
        public int size() {
            return _delegate.size();
        }

        @Override
        public Object[] toArray() {
            return _delegate.toArray();
        }

        @Override
        public <T> T[] toArray( final T[] a ) {
            return _delegate.toArray( a );
        }

        @Override
        public UnmodifiableIterator<E> iterator() {
            return UnmodifiableIteratorImpl.wrap( _delegate.iterator() );
        }

        
    }

    private static abstract class AbstractICollection<E> implements ICollection<E> {
    

        protected abstract Collection<E> delegate();
        

        @Override
        public boolean containsAll( final Iterable<?> i ) {
            return Collections3.containsAll( delegate(), i );
        }
        
        @Override
        public boolean isEmpty() {
            return delegate().isEmpty();
        }
        
        @Override
        public boolean isNotEmpty() {
            return !delegate().isEmpty();
        }
        
    }

    private static class MutableCollectionViewImpl<E>
    implements MutableCollectionView<E> {

        private final IMutableCollectionViewImpl<E> _delegate;

        public MutableCollectionViewImpl( final IMutableCollectionViewImpl<E> delegate ) {
            _delegate = delegate;
        }

        public boolean add( final E e ) {
            return _delegate.add( e );
        }

        public void clear() {
            _delegate.clear();
        }

        public boolean contains( final Object o ) {
            return _delegate.contains( o );
        }

        @Override
        public boolean isEmpty() {
            return _delegate.isEmpty();
        }
     
        public Iterator<E> iterator() {
            return _delegate.iterator();
        }

        public boolean remove( final Object o ) {
            return _delegate.remove( o );
        }

        public int size() {
            return _delegate.size();
        }

        public Object[] toArray() {
            return _delegate.toArray();
        }

        public <T> T[] toArray( final T[] a ) {
            return _delegate.toArray( a );
        }

        @Override
        public String toString() {
            return _delegate.toString();
        }

        @Override
        public boolean addAll( final Collection<? extends E> c ) {
            return _delegate.addAll( c );

        }

        @Override
        public boolean containsAll( final Collection<?> c ) {
            return _delegate.containsAll( c );

        }

        @Override
        public boolean removeAll( final Collection<?> c ) {
            return _delegate.removeAll( c );
        }

        @Override
        public boolean retainAll( final Collection<?> c ) {
            return _delegate.retainAll( c );
        }


    }

    private static class ImmutableCollectionViewImpl<E> 
    extends ForwardingCollection<E>
    implements ImmutableCollectionView<E> {

        private final ImmutableCollection<E> _delegate;

        public ImmutableCollectionViewImpl( final ImmutableCollection<E> delegate ) {
            _delegate = delegate;
        }

        @Override
        protected Collection<E> delegate() {
            return _delegate;
        }

    }

    private static class IImmutableCollectionViewImpl<E> 
        extends AbstractICollection<E>
        implements IImmutableCollectionView<E> {

        private final ImmutableCollection<E> _delegate;

        public IImmutableCollectionViewImpl( final ImmutableCollection<E> delegate ) {
            _delegate = delegate;
        }

        @Override
        public ImmutableCollectionView<E> asCollectionsView() {
            return new ImmutableCollectionViewImpl<E>( _delegate );
        }

        @Override
        public Collection<E> copyToMutableCollections() {
            return Lists.newArrayList( _delegate );
        }

        @Override
        public IMutableCollection<E> copyToMutable() {
            return MutableArrayList.copyOf( _delegate ); 
        }

        @Override
        public boolean contains( final Object o ) {
            return _delegate.contains( o );
        }

        @Override
        public boolean isEmpty() {
            return _delegate.isEmpty();
        }

        @Override
        public int size() {
            return _delegate.size();
        }

        @Override
        public Object[] toArray() {
            return _delegate.toArray();
        }

        @Override
        public <T> T[] toArray( final T[] a ) {
            return _delegate.toArray( a );
        }

        @Override
        public ImmutableIterator<E> iterator() {
            return ImmutableIteratorImpl.wrap( _delegate.iterator() );
        }

        @Override
        protected Collection<E> delegate() {
            return _delegate;
        }

    }

    private static class IMutableCollectionViewImpl<E>
        extends AbstractICollection<E>
        implements IMutableCollectionView<E> {

        private final Collection<E> _collection;

        private IMutableCollectionViewImpl( final Collection<E> collection ) {
            _collection = collection;
        }

        @Override
        public void clear() {
            _collection.clear();
        }

        @Override
        public boolean contains( final Object o ) {
            return _collection.contains( o );
        }

        @Override
        public boolean isEmpty() {
            return _collection.isEmpty();
        }

        @Override
        public Iterator<E> iterator() {
            return _collection.iterator();
        }

        @Override
        public int size() {
            return _collection.size();
        }

        @Override
        public Object[] toArray() {
            return _collection.toArray();
        }

        @Override
        public <T> T[] toArray( final T[] a ) {
            return _collection.toArray( a );
        }

        @Override
        public boolean add( final E e ) {
            return _collection.add( e );
        }

        @Override
        public Collection<E> copyToMutableCollections() {
            return Lists.newArrayList( _collection );
        }

        @Override
        public IMutableCollectionView<E> filter( final Predicate<E> predicate ) {
            return new IMutableCollectionViewImpl<E>( Collections2.filter( _collection, predicate ) );
        }

        @Override
        public MutableCollectionView<E> asCollectionsView() {
            return new MutableCollectionViewImpl<E>( this );
        }

        @Override
        public boolean addAll( final Iterable<? extends E> c ) {
            return Iterables.addAll( _collection, c );
        }

        @Override
        public boolean remove( final Object o ) {
            return _collection.remove( o );
        }

        @Override
        protected Collection<E> delegate() {
            return _collection;
        }

        @Override
        public boolean removeAll( final Iterable<?> i ) {
            return Collections3.removeAll( _collection, i );
            
        }

        @Override
        public boolean retainAll( final Iterable<?> i ) {
            return Collections3.retainAll( _collection, i );
            
        }

        @Override
        public boolean addIfNonNull( final E e ) {
            if ( e == null ) {
                return false;
            }
            
            return add( e );
        }

        @Override
        public IMutableCollectionViewImpl<E> append( final E e ) {
            add( e );
            return this;
        }

    }

    @SuppressWarnings( "all" )
    private static class UnmodifiableCollectionViewImpl<T>
    implements org.thiesen.collections.common.view.collection.UnmodifiableCollectionView<T> {

        private final ICollection<T> _collection;

        private UnmodifiableCollectionViewImpl( final ICollection<T> collection ) {
            _collection = collection;
        }

        @Override
        public boolean contains( final Object o ) {
            return _collection.contains( o );
        }

        @Override
        public boolean isEmpty() {
            return _collection.isEmpty();
        }

        @Override
        public Iterator<T> iterator() {
            return _collection.iterator();
        }

        @Override
        public int size() {
            return _collection.size();
        }

        @Override
        public Object[] toArray() {
            return _collection.toArray();
        }

        @Override
        public boolean add( @SuppressWarnings( "unused" ) final T e ) {
            throw new UnsupportedOperationException("Unmodifieable View");
        }

        @Override
        public boolean addAll( @SuppressWarnings( "unused" ) final java.util.Collection<? extends T> c ) {
            throw new UnsupportedOperationException("Unmodifieable View");

        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Unmodifieable View");

        }

        @Override
        public boolean containsAll( @SuppressWarnings( "unused" ) final java.util.Collection<?> c ) {
            throw new UnsupportedOperationException("Unmodifieable View");

        }

        @Override
        public boolean remove( @SuppressWarnings( "unused" ) final Object o ) {
            throw new UnsupportedOperationException("Unmodifieable View");

        }

        @Override
        public boolean removeAll( @SuppressWarnings( "unused" ) final java.util.Collection<?> c ) {
            throw new UnsupportedOperationException("Unmodifieable View");

        }

        @Override
        public boolean retainAll( @SuppressWarnings( "unused" ) final java.util.Collection<?> c ) {
            throw new UnsupportedOperationException("Unmodifieable View" );
        }

        @Override
        public <E> E[] toArray( final E[] a ) {
            return _collection.<E>toArray( a );
        }

    }

    public static <E> IMutableCollectionView<E> asMutableCollectionView( final Collection<E> collection ) {
        return new IMutableCollectionViewImpl<E>( collection );
    }

    public static <E> IImmutableCollectionView<E> asIImmutableCollectionView( final ImmutableCollection<E> collection ) {
        return new IImmutableCollectionViewImpl<E>( collection );
    }

    public static <E> IUnmodifiableCollectionView<E> asIUnmodifiableCollectionView( final Collection<E> collection ) {
        return new IUnmodifiableCollectionViewImpl<E>( collection );
    }
    
}

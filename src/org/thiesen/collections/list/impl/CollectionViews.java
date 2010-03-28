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

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.collection.IMutableCollection;
import org.thiesen.collections.collection.IMutableCollectionView;
import org.thiesen.collections.collection.IUnmodifiableCollectionView;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;


class CollectionViews {

    static class IUnmodifiableCollectionViewImpl<T> implements IUnmodifiableCollectionView<T> {

        private final Collection<T> _collection;

        @SuppressWarnings( "unchecked" )
        protected IUnmodifiableCollectionViewImpl( final Collection<? extends T> collection ) {
            _collection = (Collection<T>) collection;
        }

        @Override
        public boolean contains( final Object o ) {
            return _collection.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _collection.containsAll( c.copyToMutableCollections() );
        }

        @Override
        public Collection<T> copyToMutableCollections() {
            return Lists.newArrayList( _collection );
        }

        @Override
        public boolean isEmpty() {
            return _collection.isEmpty();
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
        public <E> E[] toArray( final E[] a ) {
            return _collection.toArray( a );
        }

        @Override
        public org.thiesen.collections.common.CollectionView<T> asCollectionsView() {
            return new UnmodifiableCollectionViewImpl<T>( this );
        }

        @Override
        public <E> IUnmodifiableCollectionView<E> transform( final Function<T, E> transformFunction ) {
            return new IUnmodifiableCollectionViewImpl<E>( Collections2.transform( _collection, transformFunction ) );
        }

        @Override
        public Iterator<T> iterator() {
            return _collection.iterator();
        }

        
    }

    static class IMutableCollectionViewImpl<E> implements IMutableCollectionView<E> {

        private final Collection<E> _collection;

        @SuppressWarnings( "unchecked" )
        protected IMutableCollectionViewImpl( final Collection<? extends E> collection ) {
            _collection = (Collection<E>) collection;
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
        public boolean equals( final Object o ) {
            return _collection.equals( o );
        }

        @Override
        public int hashCode() {
            return _collection.hashCode();
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
        public boolean remove( final Object o ) {
            return _collection.remove( o );
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
        public boolean addAll( final ICollection<? extends E> c ) {
            return _collection.addAll( c.asCollectionsView() );
        }

        @Override
        public boolean removeAll( final ICollection<?> c ) {
            return _collection.removeAll( c.asCollectionsView() );
        }

        @Override
        public boolean retainAll( final ICollection<?> c ) {
            return _collection.retainAll( c.asCollectionsView() );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _collection.containsAll( c.asCollectionsView() );
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
        public org.thiesen.collections.common.CollectionView<E> asCollectionsView() {
            return new UnmodifiableCollectionViewImpl<E>( this );
        }

        @Override
        public <T> IUnmodifiableCollectionView<T> transform( final Function<E, T> transformFunction ) {
            return new IUnmodifiableCollectionViewImpl<T>( Collections2.transform( _collection, transformFunction ) );            
        }

    }

    static class UnmodifiableCollectionViewImpl<T>
        implements org.thiesen.collections.common.UnmodifiableCollectionView<T> {

        private final ICollection<T> _collection;

        @SuppressWarnings( "unchecked" )
        protected UnmodifiableCollectionViewImpl( final ICollection<? extends T> collection ) {
            _collection = (ICollection<T>) collection;
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

    public static <E> IMutableCollection<E> asMutableCollection( final Collection<? extends E> collection ) {
        return new IMutableCollectionViewImpl<E>( collection );
    }
    
    public static <E> IMutableCollectionView<E> asMutableCollectionView( final Collection<? extends E> collection ) {
        return new IMutableCollectionViewImpl<E>( collection );
    }
    
}

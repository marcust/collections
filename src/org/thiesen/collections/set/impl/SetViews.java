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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.common.ImmutableIterator;
import org.thiesen.collections.common.ImmutableIteratorImpl;
import org.thiesen.collections.common.ImmutableSetView;
import org.thiesen.collections.common.MutableSetView;
import org.thiesen.collections.common.UnmodifiableIteratorImpl;
import org.thiesen.collections.common.UnmodifiableSetView;
import org.thiesen.collections.set.IImmutableSet;
import org.thiesen.collections.set.IImmutableSetView;
import org.thiesen.collections.set.IMutableSet;
import org.thiesen.collections.set.IMutableSetView;
import org.thiesen.collections.set.IUnmodifiableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;

class SetViews {
    
    // FIXME(MT): The Views do not honor the difference between sorted und unsorted

    private static class UnmodifiableSetViewImpl<E> implements UnmodifiableSetView<E> {

        private final Set<E> _set;

        public UnmodifiableSetViewImpl( final Set<E> set ) {
            _set = Collections.unmodifiableSet( set );
        }

        public boolean add( @SuppressWarnings( "unused" ) final E e ) {
            throw new UnsupportedOperationException("Unmodifiable View");
        }

        public boolean addAll( @SuppressWarnings( "unused" ) final Collection<? extends E> c ) {
            throw new UnsupportedOperationException("Unmodifiable View");
        }

        public void clear() {
            throw new UnsupportedOperationException("Unmodifiable View");
        }

        public boolean contains( final Object o ) {
            return _set.contains( o );
        }

        public boolean containsAll( final Collection<?> c ) {
            return _set.containsAll( c );
        }

        public boolean isEmpty() {
            return _set.isEmpty();
        }

        public UnmodifiableIterator<E> iterator() {
            return UnmodifiableIteratorImpl.wrap( _set.iterator() );
        }

        public boolean remove( @SuppressWarnings( "unused" ) final Object o ) {
            throw new UnsupportedOperationException("Unmodifiable View");
        }

        public boolean removeAll( @SuppressWarnings( "unused" ) final Collection<?> c ) {
            throw new UnsupportedOperationException("Unmodifiable View");
        }

        public boolean retainAll( @SuppressWarnings( "unused" ) final Collection<?> c ) {
            throw new UnsupportedOperationException("Unmodifiable View");
        }

        public int size() {
            return _set.size();
        }

        public Object[] toArray() {
            return _set.toArray();
        }

        public <T> T[] toArray( final T[] a ) {
            return _set.toArray( a );
        }

        
    }

    private static class IUnmodifiableSetViewImpl<E> implements IUnmodifiableSetView<E> {

        private final Set<E> _set;

        public IUnmodifiableSetViewImpl( final Set<E> set ) {
            _set = Collections.unmodifiableSet( set );
        }
        
        @Override
        public UnmodifiableSetView<E> asCollectionsView() {
            return new UnmodifiableSetViewImpl<E>( _set );
        }

        @Override
        public boolean containsAll( final Collection<?> c ) {
            return _set.containsAll( c );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return Sets.newHashSet( _set );
        }

        @Override
        public IUnmodifiableSetView<E> filter( final Predicate<E> predicate ) {
            return new IUnmodifiableSetViewImpl<E>( Sets.filter( _set, predicate ) );
        }

        @Override
        public UnmodifiableIterator<E> iterator() {
            return UnmodifiableIteratorImpl.wrap( _set.iterator() );
        }

        @Override
        public boolean contains( final Object o ) {
            return _set.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _set.containsAll( c.asCollectionsView() );
        }

        @Override
        public boolean isEmpty() {
            return _set.isEmpty();
        }

        @Override
        public int size() {
            return _set.size();
        }

        @Override
        public Object[] toArray() {
            return _set.toArray();
        }

        @Override
        public <T> T[] toArray( final T[] a ) {
            return _set.toArray( a );
        }

    }

    private static class ImmutableSetViewImpl<E> implements ImmutableSetView<E> {

        private final Set<E> _set;

        public ImmutableSetViewImpl( final Set<E> set ) {
            _set = Collections.unmodifiableSet( set );
        }

        public boolean add( @SuppressWarnings( "unused" ) final E e ) {
            throw new UnsupportedOperationException("Immutable Set View");
        }

        public boolean addAll( @SuppressWarnings( "unused" ) final Collection<? extends E> c ) {
            throw new UnsupportedOperationException("Immutable Set View");
        }

        public void clear() {
            throw new UnsupportedOperationException("Immutable Set View");
        }

        public boolean contains( final Object o ) {
            return _set.contains( o );
        }

        public boolean containsAll( final Collection<?> c ) {
            return _set.containsAll( c );
        }

        public boolean isEmpty() {
            return _set.isEmpty();
        }

        public ImmutableIterator<E> iterator() {
            return ImmutableIteratorImpl.wrap( _set.iterator() );
        }

        public boolean remove( @SuppressWarnings( "unused" ) final Object o ) {
            throw new UnsupportedOperationException("Immutable Set View");
        }

        public boolean removeAll( @SuppressWarnings( "unused" ) final Collection<?> c ) {
            throw new UnsupportedOperationException("Immutable Set View");
        }

        public boolean retainAll( @SuppressWarnings( "unused" ) final Collection<?> c ) {
            throw new UnsupportedOperationException("Immutable Set View");
        }

        public int size() {
            return _set.size();
        }

        public Object[] toArray() {
            return _set.toArray();
        }

        public <T> T[] toArray( final T[] a ) {
            return _set.toArray( a );
        }

    }

    private static class IImmutableSetViewImpl<E> implements IImmutableSetView<E> {

        private final Set<E> _set;

        private IImmutableSetViewImpl( final Set<E> set ) {
            _set = Collections.unmodifiableSet( set );
        }

        @Override
        public ImmutableSetView<E> asCollectionsView() {
            return new ImmutableSetViewImpl<E>( _set );
            
        }

        @Override
        public boolean containsAll( final Collection<?> c ) {
            return _set.containsAll( c );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return Sets.newHashSet( _set );
        }

        @Override
        public IImmutableSetView<E> filter( final Predicate<E> predicate ) {
            return new IImmutableSetViewImpl<E>( Sets.filter( _set, predicate ) );
        }

        @Override
        public ImmutableIterator<E> iterator() {
            return ImmutableIteratorImpl.wrap( _set.iterator() );
        }

        @Override
        public boolean contains( final Object o ) {
            return _set.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _set.containsAll( c.asCollectionsView() );
        }

        @Override
        public boolean isEmpty() {
            return _set.isEmpty();
        }

        @Override
        public int size() {
            return _set.size();
        }

        @Override
        public Object[] toArray() {
            return _set.toArray();
        }

        @Override
        public <T> T[] toArray( final T[] a ) {
            return _set.toArray( a );
        }

        @Override
        public IMutableSet<E> mutableCopy() {
            return MutableHashSet.copyOf( _set );
        }
    }



    private static class MutableSetViewImpl<E> implements MutableSetView<E> {

        private final Set<E> _set;

        public MutableSetViewImpl( final Set<E> set ) {
            _set = set;
        }

        public boolean add( final E e ) {
            return _set.add( e );
        }

        public boolean addAll( final Collection<? extends E> c ) {
            return _set.addAll( c );
        }

        public void clear() {
            _set.clear();
        }

        public boolean contains( final Object o ) {
            return _set.contains( o );
        }

        public boolean containsAll( final Collection<?> c ) {
            return _set.containsAll( c );
        }

        public boolean isEmpty() {
            return _set.isEmpty();
        }

        public Iterator<E> iterator() {
            return _set.iterator();
        }

        public boolean remove( final Object o ) {
            return _set.remove( o );
        }

        public boolean removeAll( final Collection<?> c ) {
            return _set.removeAll( c );
        }

        public boolean retainAll( final Collection<?> c ) {
            return _set.retainAll( c );
        }

        public int size() {
            return _set.size();
        }

        public Object[] toArray() {
            return _set.toArray();
        }

        public <T> T[] toArray( final T[] a ) {
            return _set.toArray( a );
        }
        
    }

    private static class IMutableSetViewImpl<E> implements IMutableSetView<E> {

        private final Set<E> _set;

        public IMutableSetViewImpl( final Set<E> set ) {
            _set = set;
        }

        public boolean add( final E e ) {
            return _set.add( e );
        }

        public boolean addAll( final Collection<? extends E> c ) {
            return _set.addAll( c );
        }

        public void clear() {
            _set.clear();
        }

        public boolean contains( final Object o ) {
            return _set.contains( o );
        }

        public boolean containsAll( final Collection<?> c ) {
            return _set.containsAll( c );
        }

        public boolean isEmpty() {
            return _set.isEmpty();
        }

        public Iterator<E> iterator() {
            return _set.iterator();
        }

        public boolean remove( final Object o ) {
            return _set.remove( o );
        }

        public boolean removeAll( final Collection<?> c ) {
            return _set.removeAll( c );
        }

        public boolean retainAll( final Collection<?> c ) {
            return _set.retainAll( c );
        }

        public int size() {
            return _set.size();
        }

        public Object[] toArray() {
            return _set.toArray();
        }

        public <T> T[] toArray( final T[] a ) {
            return _set.toArray( a );
        }

        @Override
        public MutableSetView<E> asCollectionsView() {
            return new MutableSetViewImpl<E>( _set );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return Sets.newHashSet( _set );
        }

        @Override
        public IMutableSetView<E> filter( final Predicate<E> predicate ) {
            return new IMutableSetViewImpl<E>( Sets.filter( _set, predicate ) );
            
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _set.containsAll( c.asCollectionsView() );
        }

        @Override
        public IImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf( _set );
        }

        @Override
        public IUnmodifiableSetView<E> asUnmodifiableView() {
            return new IUnmodifiableSetViewImpl<E>( _set );
        }
        
 
    }

    static <E> IMutableSetView<E> asIMutableSetView( final Set<E> set ) {
        return new IMutableSetViewImpl<E>( set );
    }
    
    static <E> MutableSetView<E> asMutableSetView( final Set<E> set ) {
        return new MutableSetViewImpl<E>( set );
    }
    
    static <E> ImmutableSetView<E> asImmutableSetView( final Set<E> set ) {
        return new ImmutableSetViewImpl<E>( set );
    }
    
    static <E> IImmutableSetView<E> asIImmutableSetView( final Set<E> set ) {
        return new IImmutableSetViewImpl<E>( set );
    }
    
    static <E> IUnmodifiableSetView<E> asIUnmodifiableSetView( final Set<E> set ) {
        return new IUnmodifiableSetViewImpl<E>( set );
    }
    
}

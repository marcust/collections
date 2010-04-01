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
import org.thiesen.collections.collection.impl.Collections3;
import org.thiesen.collections.common.iterator.ImmutableIterator;
import org.thiesen.collections.common.iterator.ImmutableIteratorImpl;
import org.thiesen.collections.common.iterator.UnmodifiableIteratorImpl;
import org.thiesen.collections.common.view.set.ImmutableSetView;
import org.thiesen.collections.common.view.set.MutableSetView;
import org.thiesen.collections.common.view.set.UnmodifiableSetView;
import org.thiesen.collections.set.IImmutableSet;
import org.thiesen.collections.set.IMutableSet;
import org.thiesen.collections.set.ISet;
import org.thiesen.collections.set.views.IImmutableSetView;
import org.thiesen.collections.set.views.IMutableSetView;
import org.thiesen.collections.set.views.IUnmodifiableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;

public class SetViews {
    
    // FIXME(MT): The Views do not honor the difference between sorted und unsorted

    static abstract class AbstractISet<E> implements ISet<E> {
        
        protected abstract Set<E> delegate();
        

        @Override
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( delegate(), c );
        }
        
    }
    
    static abstract class AbstractIMutableSet<E>
        extends AbstractISet<E> implements IMutableSet<E> {
        
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
    
    private static class UnmodifiableSetViewImpl<E> 
        extends ForwardingSet<E>
        implements UnmodifiableSetView<E> {

        private final Set<E> _delegate;

        public UnmodifiableSetViewImpl( final Set<E> set ) {
            _delegate = Collections.unmodifiableSet( set );
        }

        @Override
        protected Set<E> delegate() {
            return _delegate;
        }
        
    }

    private static class IUnmodifiableSetViewImpl<E>
        extends AbstractISet<E>
        implements IUnmodifiableSetView<E> {

        private final Set<E> _delegate;

        public IUnmodifiableSetViewImpl( final Set<E> set ) {
            _delegate = Collections.unmodifiableSet( set );
        }
        
        @Override
        public UnmodifiableSetView<E> asCollectionsView() {
            return new UnmodifiableSetViewImpl<E>( _delegate );
        }

        @Override
        public boolean containsAll( final Collection<?> c ) {
            return _delegate.containsAll( c );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return Sets.newHashSet( _delegate );
        }

        @Override
        public IUnmodifiableSetView<E> filter( final Predicate<E> predicate ) {
            return new IUnmodifiableSetViewImpl<E>( Sets.filter( _delegate, predicate ) );
        }

        @Override
        public UnmodifiableIterator<E> iterator() {
            return UnmodifiableIteratorImpl.wrap( _delegate.iterator() );
        }

        @Override
        public boolean contains( final Object o ) {
            return _delegate.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _delegate.containsAll( c.asCollectionsView() );
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
        protected Set<E> delegate() {
            return _delegate;
        }

    }

    private static class ImmutableSetViewImpl<E> 
        extends ForwardingSet<E>
        implements ImmutableSetView<E> {

        private final Set<E> _delegate;

        public ImmutableSetViewImpl( final Set<E> set ) {
            _delegate = Collections.unmodifiableSet( set );
        }

        @Override
        protected Set<E> delegate() {
            return _delegate;
        }

       
    }

    private static class IImmutableSetViewImpl<E> 
        extends AbstractISet<E>
        implements IImmutableSetView<E> {

        private final Set<E> _delegate;

        private IImmutableSetViewImpl( final Set<E> set ) {
            _delegate = Collections.unmodifiableSet( set );
        }

        @Override
        public ImmutableSetView<E> asCollectionsView() {
            return new ImmutableSetViewImpl<E>( _delegate );
            
        }

        @Override
        public boolean containsAll( final Collection<?> c ) {
            return _delegate.containsAll( c );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return Sets.newHashSet( _delegate );
        }

        @Override
        public IImmutableSetView<E> filter( final Predicate<E> predicate ) {
            return new IImmutableSetViewImpl<E>( Sets.filter( _delegate, predicate ) );
        }

        @Override
        public ImmutableIterator<E> iterator() {
            return ImmutableIteratorImpl.wrap( _delegate.iterator() );
        }

        @Override
        public boolean contains( final Object o ) {
            return _delegate.contains( o );
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _delegate.containsAll( c.asCollectionsView() );
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
        public IMutableSet<E> mutableCopy() {
            return MutableHashSet.copyOf( _delegate );
        }

        @Override
        protected Set<E> delegate() {
            return _delegate;
        }
    }



    private static class MutableSetViewImpl<E> 
        extends ForwardingSet<E>
        implements MutableSetView<E> {

        private final Set<E> _delegate;

        public MutableSetViewImpl( final Set<E> set ) {
            _delegate = set;
        }

        @Override
        protected Set<E> delegate() {
            return _delegate;
        }

        
    }

    private static class IMutableSetViewImpl<E> 
        extends AbstractIMutableSet<E>
        implements IMutableSetView<E> {

        private final Set<E> _delegate;

        public IMutableSetViewImpl( final Set<E> set ) {
            _delegate = set;
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

        public boolean containsAll( final Collection<?> c ) {
            return _delegate.containsAll( c );
        }

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
        public MutableSetView<E> asCollectionsView() {
            return new MutableSetViewImpl<E>( _delegate );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return Sets.newHashSet( _delegate );
        }

        @Override
        public IMutableSetView<E> filter( final Predicate<E> predicate ) {
            return new IMutableSetViewImpl<E>( Sets.filter( _delegate, predicate ) );
            
        }

        @Override
        public boolean containsAll( final ICollection<?> c ) {
            return _delegate.containsAll( c.asCollectionsView() );
        }

        @Override
        public IImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf( _delegate );
        }

        @Override
        public IUnmodifiableSetView<E> asUnmodifiableView() {
            return new IUnmodifiableSetViewImpl<E>( _delegate );
        }

        @Override
        protected Set<E> delegate() {
            return _delegate;
        }
        
 
    }

    public static <E> IMutableSetView<E> asIMutableSetView( final Set<E> set ) {
        return new IMutableSetViewImpl<E>( set );
    }
    
    public static <E> MutableSetView<E> asMutableSetView( final Set<E> set ) {
        return new MutableSetViewImpl<E>( set );
    }
    
    public static <E> ImmutableSetView<E> asImmutableSetView( final Set<E> set ) {
        return new ImmutableSetViewImpl<E>( set );
    }
    
    public static <E> IImmutableSetView<E> asIImmutableSetView( final Set<E> set ) {
        return new IImmutableSetViewImpl<E>( set );
    }
    
    public static <E> IUnmodifiableSetView<E> asIUnmodifiableSetView( final Set<E> set ) {
        return new IUnmodifiableSetViewImpl<E>( set );
    }
    
}

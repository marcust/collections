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

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.thiesen.collections.collection.impl.Collections3;
import org.thiesen.collections.common.iterator.ImmutableIterator;
import org.thiesen.collections.common.iterator.ImmutableIteratorImpl;
import org.thiesen.collections.common.iterator.UnmodifiableIteratorImpl;
import org.thiesen.collections.common.view.set.ImmutableSetView;
import org.thiesen.collections.common.view.set.ImmutableSortedSetView;
import org.thiesen.collections.common.view.set.MutableSetView;
import org.thiesen.collections.common.view.set.MutableSortedSetView;
import org.thiesen.collections.common.view.set.UnmodifiableSetView;
import org.thiesen.collections.set.IImmutableSet;
import org.thiesen.collections.set.IImmutableSortedSet;
import org.thiesen.collections.set.IMutableSet;
import org.thiesen.collections.set.IMutableSortedSet;
import org.thiesen.collections.set.ISet;
import org.thiesen.collections.set.views.IImmutableSetView;
import org.thiesen.collections.set.views.IImmutableSortedSetView;
import org.thiesen.collections.set.views.IMutableSetView;
import org.thiesen.collections.set.views.IMutableSortedSetView;
import org.thiesen.collections.set.views.IUnmodifiableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ForwardingSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;

public class SetViews {
    
    private static class ImmutableSortedSetViewImpl<E> 
        extends ForwardingSortedSet<E>
        implements ImmutableSortedSetView<E> {

        private final SortedSet<E> _delegate;

        public ImmutableSortedSetViewImpl( final SortedSet<E> delegate ) {
            _delegate = Collections.unmodifiableSortedSet( delegate );
        }

        @Override
        protected SortedSet<E> delegate() {
            return _delegate;
        }

    }

    private static class IImmutableSortedSetViewImpl<E> implements IImmutableSortedSetView<E> {

        private final SortedSet<E> _delegate;

        public IImmutableSortedSetViewImpl( final SortedSet<E> set ) {
            _delegate = Collections.unmodifiableSortedSet( set );
        }

        @Override
        public ImmutableSortedSetView<E> asCollectionsView() {
            return new ImmutableSortedSetViewImpl<E>( _delegate );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return new TreeSet<E>( _delegate );
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
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( _delegate, c );
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
        public IMutableSortedSet<E> mutableCopy() {
            return org.thiesen.collections.set.impl.MutableTreeSet.copyOf( this );
        }

        @Override
        public Comparator<? super E> comparator() {
            return _delegate.comparator();
            
        }

        @Override
        public E first() {
            return _delegate.first();
        }

        @Override
        public IImmutableSortedSetView<E> headSet( final E toElement ) {
            return new IImmutableSortedSetViewImpl<E>( _delegate.headSet( toElement ) );
        }

        @Override
        public E last() {
            return _delegate.last();
        }

        @Override
        public IImmutableSortedSetView<E> subSet( final E fromElement, final E toElement ) {
            return new IImmutableSortedSetViewImpl<E>( _delegate.subSet( fromElement, toElement ) );
        }

        @Override
        public IImmutableSortedSetView<E> tailSet( final E fromElement ) {
            return new IImmutableSortedSetViewImpl<E>( _delegate.tailSet( fromElement ) );
        }

    }

    private static class MutableSortedSetViewImpl<E>
        extends ForwardingSortedSet<E>
        implements MutableSortedSetView<E> {

        private final SortedSet<E> _delegate;

        public MutableSortedSetViewImpl( final SortedSet<E> delegate ) {
            _delegate = delegate;
        }

        @Override
        protected SortedSet<E> delegate() {
            return _delegate;
        }

    }

    private static class IMutableSortedSetViewImpl<E> implements IMutableSortedSetView<E> {

        private final SortedSet<E> _set;

        public IMutableSortedSetViewImpl( final SortedSet<E> set ) {
            _set = set;
        }

        @Override
        public MutableSortedSetView<E> asCollectionsView() {
            return new MutableSortedSetViewImpl<E>( _set ); 
        }

        @Override
        public Comparator<? super E> comparator() {
            return _set.comparator();
        }

        @Override
        public E first() {
            return _set.first();
        }

        @Override
        public IMutableSortedSetView<E> headSet( final E toElement ) {
            return new IMutableSortedSetViewImpl<E>( _set.headSet( toElement ) );
        }

        @Override
        public E last() {
            return _set.last();
        }

        @Override
        public IMutableSortedSetView<E> subSet( final E fromElement, final E toElement ) {
            return new IMutableSortedSetViewImpl<E>( _set.subSet( fromElement, toElement ) );
        }

        @Override
        public IMutableSortedSetView<E> tailSet( final E fromElement ) {
            return new IMutableSortedSetViewImpl<E>( _set.tailSet( fromElement ) );
        }

        @Override
        public Set<E> copyToMutableCollections() {
            return new TreeSet<E>( _set );           
        }

        @Override
        public IMutableSetView<E> filter( final Predicate<E> predicate ) {
            return new IMutableSetViewImpl<E>( Sets.filter( _set, predicate ) );
        }

        @Override
        public Iterator<E> iterator() {
            return _set.iterator();
        }

        @Override
        public boolean contains( final Object o ) {
            return _set.contains( o );
        }

        @Override
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( _set, c );
        }

        @Override
        public boolean isEmpty() {
            return _set.isEmpty();
        }
        
        @Override
        public boolean isNotEmpty() {
            return !isEmpty();
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
        public boolean add( final E e ) {
            return _set.add( e );
        }

        @Override
        public boolean addAll( final Iterable<? extends E> c ) {
            return Iterables.addAll( _set, c );
        }

        @Override
        public IUnmodifiableSetView<E> asUnmodifiableView() {
            return new IUnmodifiableSetViewImpl<E>( _set );
        }

        @Override
        public void clear() {
            _set.clear();
        }

        @Override
        public IImmutableSortedSet<E> immutableCopy() {
            return ImmutableSortedSet.copyOf( this );
        }

        @Override
        public boolean remove( final Object o ) {
            return _set.remove( o );
        }

        @Override
        public boolean removeAll( final Iterable<?> c ) {
            return Collections3.removeAll( _set, c );
        }

        @Override
        public boolean retainAll( final Iterable<?> c ) {
            return Collections3.removeAll( _set, c );
        }

    }

    static abstract class AbstractISet<E> implements ISet<E> {
        
        protected abstract Set<E> delegate();
        

        @Override
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( delegate(), c );
        }
        
        @Override
        public boolean isNotEmpty() {
            return !isEmpty();
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
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( _delegate, c );
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
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( _delegate, c );
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
        public boolean containsAll( final Iterable<?> c ) {
            return Collections3.containsAll( _delegate, c );
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
    
    public static <E> IMutableSortedSetView<E> asIMutableSortedSetView( final SortedSet<E> set ) {
        return new IMutableSortedSetViewImpl<E>( set );
    }
    
    public static <E> MutableSortedSetView<E> asMutableSortedSetView( final SortedSet<E> set ) {
        return new MutableSortedSetViewImpl<E>( set );
    }
    
    public static <E> IImmutableSortedSetView<E> asIImmutableSortedSetView( final SortedSet<E> set ) {
        return new IImmutableSortedSetViewImpl<E>( set );
    }
    
    public static <E> ImmutableSortedSetView<E> asImmutableSortedSetView( final SortedSet<E> set ) {
        return new ImmutableSortedSetViewImpl<E>( set );
    }
}

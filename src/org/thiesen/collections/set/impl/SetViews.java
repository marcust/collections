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
import java.util.Iterator;
import java.util.Set;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.common.MutableSetView;
import org.thiesen.collections.set.IMutableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

class SetViews {

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
        
 
    }

    static <E> IMutableSetView<E> asIMutableSetView( final Set<E> set ) {
        return new IMutableSetViewImpl<E>( set );
    }
    
    static <E> MutableSetView<E> asMutableSetView( final Set<E> set ) {
        return new MutableSetViewImpl<E>( set );
    }
    
}

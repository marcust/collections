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
import java.util.Set;

import org.thiesen.collections.collection.impl.Collections3;
import org.thiesen.collections.common.iterator.ImmutableIterator;
import org.thiesen.collections.common.iterator.ImmutableIteratorImpl;
import org.thiesen.collections.common.view.set.ImmutableSetView;
import org.thiesen.collections.set.IImmutableSet;
import org.thiesen.collections.set.views.IImmutableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

abstract class AbstractDelegatingImmutableSet<E> 
    implements IImmutableSet<E> {

    private final Set<E> _set;

    public AbstractDelegatingImmutableSet( final Set<E> set ) {
        _set = set;
    }
    
    AbstractDelegatingImmutableSet() {
        _set = null;
        // for deserialization
    }
    
    @Override
    public boolean contains( final Object arg0 ) {
        return _set.contains( arg0 );
    }

    public boolean containsAll( final Collection<?> arg0 ) {
        return _set.containsAll( arg0 );
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
    public ImmutableIterator<E> iterator() {
        return ImmutableIteratorImpl.wrap( _set.iterator() );
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
    public <T> T[] toArray( final T[] arg0 ) {
        return _set.toArray( arg0 );
    }

    @Override
    public boolean containsAll( final Iterable<?> i ) {
        return Collections3.containsAll( _set, i );
    }

    @Override
    public String toString() {
        return _set.toString();
    }

    @Override
    public ImmutableSetView<E> asCollectionsView() {
        return SetViews.asImmutableSetView( _set );
    }

    @Override
    public Set<E> copyToMutableCollections() {
        return Sets.newHashSet( _set );
    }

    @Override
    public IImmutableSetView<E> filter( final Predicate<E> predicate ) {
        return SetViews.asIImmutableSetView( Sets.filter( _set, predicate ) );
    }

    @Override
    public boolean hasSingleValueOnly() {
        return Iterables.size( _set ) == 1;
    }

    @Override
    public E getSingleValue() {
        return Iterables.getOnlyElement( _set );
    }

}

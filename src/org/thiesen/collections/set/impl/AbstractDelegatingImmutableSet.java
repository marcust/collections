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

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.common.ImmutableIterator;
import org.thiesen.collections.common.ImmutableIteratorImpl;
import org.thiesen.collections.common.ImmutableSetView;
import org.thiesen.collections.set.IImmutableSet;
import org.thiesen.collections.set.IImmutableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

abstract class AbstractDelegatingImmutableSet<E> implements IImmutableSet<E> {

    private final Set<E> _set;

    public AbstractDelegatingImmutableSet( final Set<E> set ) {
        _set = set;
    }

    public boolean contains( final Object arg0 ) {
        return _set.contains( arg0 );
    }

    public boolean containsAll( final Collection<?> arg0 ) {
        return _set.containsAll( arg0 );
    }

    public boolean isEmpty() {
        return _set.isEmpty();
    }

    public ImmutableIterator<E> iterator() {
        return ImmutableIteratorImpl.wrap( _set.iterator() );
    }

    public int size() {
        return _set.size();
    }

    public Object[] toArray() {
        return _set.toArray();
    }

    public <T> T[] toArray( final T[] arg0 ) {
        return _set.toArray( arg0 );
    }

    @Override
    public boolean containsAll( final ICollection<?> c ) {
        return _set.containsAll( c.asCollectionsView() );
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
    
    
}
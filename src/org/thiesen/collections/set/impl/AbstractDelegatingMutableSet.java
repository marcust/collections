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
import org.thiesen.collections.set.IImmutableSet;
import org.thiesen.collections.set.IMutableSet;
import org.thiesen.collections.set.IMutableSetView;
import org.thiesen.collections.set.IUnmodifiableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

public abstract class AbstractDelegatingMutableSet<E> 
    implements IMutableSet<E> {

    private final Set<E> _set;

    protected AbstractDelegatingMutableSet( final Set<E> set ) {
        _set = set;
    }

    @Override
    public boolean add( final E e ) {
        return _set.add( e );
    }

    @Override
    public boolean addAll( final Collection<? extends E> c ) {
        return _set.addAll( c );
    }

    @Override
    public void clear() {
        _set.clear();
    }

    @Override
    public boolean remove( final Object o ) {
        return _set.remove( o );
    }

    @Override
    public boolean removeAll( final Collection<?> c ) {
        return _set.removeAll( c );
    }

    @Override
    public boolean retainAll( final Collection<?> c ) {
        return _set.retainAll( c );
    }

    @Override
    public boolean containsAll( final Collection<?> c ) {
        return _set.containsAll( c );
    }

    @Override
    public Iterator<E> iterator() {
        return _set.iterator();
    }

    @Override
    public MutableSetView<E> asCollectionsView() {
        return SetViews.asMutableSetView( _set );
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
    public IMutableSetView<E> filter( final Predicate<E> predicate ) {
        return SetViews.asIMutableSetView( Sets.filter( _set, predicate ) );
    }

    @Override
    public IImmutableSet<E> immutableCopy() {
        return ImmutableSet.copyOf( _set );
    }
    
    @Override
    public IUnmodifiableSetView<E> asUnmodifiableView() {
        throw new UnsupportedOperationException("Auto generated method stub");
        
    }
    
}

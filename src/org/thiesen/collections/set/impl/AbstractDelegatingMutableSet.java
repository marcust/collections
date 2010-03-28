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
import org.thiesen.collections.common.view.set.MutableSetView;
import org.thiesen.collections.set.IImmutableSet;
import org.thiesen.collections.set.IMutableSet;
import org.thiesen.collections.set.views.IMutableSetView;
import org.thiesen.collections.set.views.IUnmodifiableSetView;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

public abstract class AbstractDelegatingMutableSet<E> 
    implements IMutableSet<E> {

    private final Set<E> _delegate;

    protected AbstractDelegatingMutableSet( final Set<E> set ) {
        _delegate = set;
    }

    @Override
    public boolean add( final E e ) {
        return _delegate.add( e );
    }

    @Override
    public boolean addAll( final Collection<? extends E> c ) {
        return _delegate.addAll( c );
    }

    @Override
    public void clear() {
        _delegate.clear();
    }

    @Override
    public boolean remove( final Object o ) {
        return _delegate.remove( o );
    }

    @Override
    public boolean removeAll( final Collection<?> c ) {
        return _delegate.removeAll( c );
    }

    @Override
    public boolean retainAll( final Collection<?> c ) {
        return _delegate.retainAll( c );
    }

    @Override
    public boolean containsAll( final Collection<?> c ) {
        return _delegate.containsAll( c );
    }

    @Override
    public Iterator<E> iterator() {
        return _delegate.iterator();
    }

    @Override
    public MutableSetView<E> asCollectionsView() {
        return SetViews.asMutableSetView( _delegate );
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
    public IMutableSetView<E> filter( final Predicate<E> predicate ) {
        return SetViews.asIMutableSetView( Sets.filter( _delegate, predicate ) );
    }

    @Override
    public IImmutableSet<E> immutableCopy() {
        return ImmutableSet.copyOf( _delegate );
    }
    
    @Override
    public IUnmodifiableSetView<E> asUnmodifiableView() {
       return SetViews.asIUnmodifiableSetView( _delegate );
    }
    
}

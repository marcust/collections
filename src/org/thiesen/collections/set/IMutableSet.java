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
package org.thiesen.collections.set;

import org.thiesen.collections.common.view.set.MutableSetView;
import org.thiesen.collections.set.views.IMutableSetView;
import org.thiesen.collections.set.views.IUnmodifiableSetView;

import com.google.common.base.Predicate;

public interface IMutableSet<E> extends ISet<E> {

    boolean add(E e);
    
    IMutableSet<E> append(E e);

    boolean remove(Object o);

    boolean addAll(Iterable<? extends E> c);
    
    boolean retainAll(Iterable<?> c);
    
    boolean removeAll(Iterable<?> c);

    void clear();
    
    @Override
    public IMutableSetView<E> filter( final Predicate<E> predicate );
    
    @Override
    public MutableSetView<E> asCollectionsView();
    
    public IImmutableSet<E> immutableCopy();
    
    public IUnmodifiableSetView<E> asUnmodifiableView();
}

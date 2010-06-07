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
package org.thiesen.collections.list;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.collection.IMutableCollection;
import org.thiesen.collections.common.view.list.MutableListView;
import org.thiesen.collections.list.impl.ImmutableList;
import org.thiesen.collections.list.views.IMutableListView;
import org.thiesen.collections.list.views.IUnmodifiableListView;

public interface IMutableList<E> extends IList<E>, IMutableCollection<E> {

    void add(int index, E element);
    
    IMutableList<E> append( E element );
    
    E remove(int index);

    boolean addAll(int index, ICollection<? extends E> c);
    
    IMutableListView<E> subList(int fromIndex, int toIndex);
    
    @Override
    public MutableListView<E> asCollectionsView();

    public ImmutableList<E> immutableCopy();
    
    public IUnmodifiableListView<E> asUnmodifiableView();
    
}

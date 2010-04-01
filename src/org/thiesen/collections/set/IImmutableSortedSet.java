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

import org.thiesen.collections.common.view.set.ImmutableSortedSetView;
import org.thiesen.collections.set.views.IImmutableSortedSetView;

public interface IImmutableSortedSet<E> extends ISortedSet<E>, IImmutableSet<E> {

    IImmutableSortedSetView<E> subSet(E fromElement, E toElement);
    
    IImmutableSortedSetView<E> headSet(E toElement);
    
    IImmutableSortedSetView<E> tailSet(E fromElement);
    
    public IMutableSortedSet<E> mutableCopy();

    ImmutableSortedSetView<E> asCollectionsView();

}

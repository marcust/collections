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

import java.util.Comparator;

import org.thiesen.collections.common.view.set.SortedSetView;
import org.thiesen.collections.set.views.ISortedSetView;

public interface ISortedSet<E> extends ISet<E> {

    Comparator<? super E> comparator();

    E first();

    E last();
 
    ISortedSetView<E> subSet(E fromElement, E toElement);
    
    ISortedSetView<E> headSet(E toElement);
    
    ISortedSetView<E> tailSet(E fromElement);
    
    @Override
    public SortedSetView<E> asCollectionsView();
    
}

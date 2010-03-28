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

import java.util.Collection;

import org.thiesen.collections.collection.ICollection;
import org.thiesen.collections.common.SetView;

import com.google.common.base.Predicate;


public interface ISet<E> extends Iterable<E>, ICollection<E> {

    boolean containsAll(Collection<?> c);

    @Override
    public java.util.Set<E> copyToMutableCollections();

    @Override
    public SetView<E> asCollectionsView();
    
    ISetView<E> filter( Predicate<E> predicate );

}

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
package org.thiesen.collections.common;

import java.util.Arrays;

import org.thiesen.collections.common.view.list.UnmodifiableListView;
import org.thiesen.collections.list.impl.ListViews;
import org.thiesen.collections.list.views.IUnmodifiableListView;

public class ArrayViews {

    public static <E> IUnmodifiableListView<E> asIUnmodifiableListView( final E... entries ) {
        return ListViews.asIUnmodifiableListView( Arrays.asList( entries ) );
    }
    
    public static <E> UnmodifiableListView<E> asUnmodifiableListView( final E... entries ) {
        return ListViews.asUnmodifiableListView( Arrays.asList( entries ) );
    }
}

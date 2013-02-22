/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Comparator;

/**
 *
 * @author gmochid
 */
public class ArrangementComparator implements Comparator<Arrangement> {
    public int compare(Arrangement a, Arrangement b) {
        if (a.calculateTotalEnlightenment() > b.calculateTotalEnlightenment())
            return 1;
        else if (a.calculateTotalEnlightenment() == b.calculateTotalEnlightenment())
            return 0;
        else
            return -1;
    }
}
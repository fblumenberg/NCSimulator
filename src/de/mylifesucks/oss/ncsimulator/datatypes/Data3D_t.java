/*
 *  Copyright (C) 2010-2011 by Claas Anders "CaScAdE" Rathje
 *  admiralcascade@gmail.com
 *  Licensed under: Creative Commons / Non Commercial / Share Alike
 *  http://creativecommons.org/licenses/by-nc-sa/2.0/de/
 *
 */
package de.mylifesucks.oss.ncsimulator.datatypes;

import de.mylifesucks.oss.ncsimulator.datastorage.DataStorage;
import java.util.LinkedList;

/**
 * 3D Data Struct
 * adapted from: http://svn.mikrokopter.de/filedetails.php?repname=FlightCtrl&path=/tags/V0.82a/uart.h
 * @author Claas Anders "CaScAdE" Rathje
 */
public class Data3D_t extends c_int {

    public s16 AngleNick = new s16("AngleNick",-1800,1800);	// in 0.1 deg
    public s16 AngleRoll = new s16("AngleRoll",-1800,1800);   // in 0.1 deg
    public s16 Heading = new s16("Heading",-1800,1800); 	// in 0.1 deg
    
    public s8 CentroidNick = new s8("CentroidNick");
    public s8 CentroidRoll = new s8("CentroidRoll");
    public s8 CentroidYaw = new s8("CentroidYaw");

    public u8 reserve[] = new u8[5];

    public Data3D_t() {
        allAttribs = new LinkedList<c_int>();
        allAttribs.add(AngleNick);
        allAttribs.add(AngleRoll);
        allAttribs.add(Heading);
        allAttribs.add(CentroidNick);
        allAttribs.add(CentroidRoll);
        allAttribs.add(CentroidYaw);
        
        for (int i = 0; i < reserve.length; i++) {
            reserve[i] = new u8("reserve" + i);
            allAttribs.add(reserve[i]);
        }

        for (c_int c : allAttribs) {
            DataStorage.addToSerializePool(c);
        }
    }
}

/*
 *  Copyright (C) 2010-2011 by Claas Anders "CaScAdE" Rathje
 *  admiralcascade@gmail.com
 *  Licensed under: Creative Commons / Non Commercial / Share Alike
 *  http://creativecommons.org/licenses/by-nc-sa/2.0/de/
 *
 */
package de.mylifesucks.oss.ncsimulator.datatypes;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * Navi-OSD-Data Struct
 * adapted from: http://svn.mikrokopter.de/filedetails.php?repname=NaviCtrl&path=/tags/V0.22a/uart1.h
 *
 *  @author Claas Anders "CaScAdE" Rathje
 */
public class NaviData_t extends c_int implements Observer{

    public static final String[] FC_FLAGS = {"MOTOR_RUN", "FLY", "CALIBRATE", "START", "NOTLANDUNG", "LOWBAT", "SPI_RX_ERR", "I2CERR"};
    public static final String[] NC_FLAGS = {"FREE", "PH", "CH", "RANGE_LIMIT", "NOSERIALLINK", "TARGET_REACHED", "MANUAL_CONTROL", "GPS_OK "};
    public static final String[] ERROR_CODE = {"FC !COMPAT", "NO FC COMM", "NO MK3MAG COMM"};
    public u8 Version = new u8("Version"); // version of the data structure
    public GPS_Pos_t CurrentPosition = new GPS_Pos_t("Current"); // see ubx.h for details
    public GPS_Pos_t TargetPosition = new GPS_Pos_t("Target");
    public GPS_PosDev_t TargetPositionDeviation = new GPS_PosDev_t("TargetDev");
    public GPS_Pos_t HomePosition = new GPS_Pos_t("Home");
    public GPS_PosDev_t HomePositionDeviation = new GPS_PosDev_t("HomeDev");
    public u8 WaypointIndex = new u8("WaypointIndex"); // index of current waypoints running from 0 to WaypointNumber-1
    public u8 WaypointNumber = new u8("WaypointNumber"); // number of stored waypoints
    public u8 SatsInUse = new u8("SatsInUse"); // number of satellites used for position solution
    public s16 Altimeter = new s16("Altimeter"); // hight according to air pressure
    public s16 Variometer = new s16("Variometer"); // climb(+) and sink(-) rate
    public u16 FlyingTime = new u16("FlyingTime"); // in seconds
    public u8 UBat = new u8("UBat"); // Battery Voltage in 0.1 Volts
    public u16 GroundSpeed = new u16("GroundSpeed"); // speed over ground in cm/s (2D)
    public s16 Heading = new s16("Heading"); // current flight direction in � as angle to north
    public s16 CompassHeading = new s16("CompassHeading"); // current compass value in �
    public s8 AngleNick = new s8("AngleNick"); // current Nick angle in 1�
    public s8 AngleRoll = new s8("AngleRoll"); // current Rick angle in 1�
    public u8 RC_Quality = new u8("RC_Quality"); // RC_Quality
    public u8Flags FCFlags = new u8Flags("FCFlags", FC_FLAGS); // Flags from FC
    public u8Flags NCFlags = new u8Flags("NCFlags", NC_FLAGS); // Flags from NC
    public u8 Errorcode = new u8("Errorcode"); // 0 --> okay
    public u8 OperatingRadius = new u8("OperatingRadius"); // current operation radius around the Home Position in m
    public s16 TopSpeed = new s16("TopSpeed"); // velocity in vertical direction in cm/s
    public u8 TargetHoldTime = new u8("TargetHoldTime"); // time in s to stay at the given target, counts down to 0 if target has been reached
    public u8 RC_RSSI = new u8("RC_RSSI"); // Receiver signal strength (since version 2 added)
    public s16 SetpointAltitude = new s16("SetpointAltitude"); // setpoint for altitude
    public u8 Gas = new u8("Gas"); // for future use
    public u16 Current = new u16("Current"); // actual current in 0.1A steps
    public u16 UsedCapacity = new u16("UsedCapacity"); // used capacity in mAh

    public NaviData_t() {
        super();
        allAttribs = new LinkedList<c_int>();
        allAttribs.add(Version);
        allAttribs.add(CurrentPosition);
        allAttribs.add(TargetPosition);
        allAttribs.add(TargetPositionDeviation);
        allAttribs.add(HomePosition);
        allAttribs.add(HomePositionDeviation);
        allAttribs.add(WaypointIndex);
        allAttribs.add(WaypointNumber);
        allAttribs.add(SatsInUse);
        allAttribs.add(Altimeter);
        allAttribs.add(Variometer);
        allAttribs.add(FlyingTime);
        allAttribs.add(UBat);
        allAttribs.add(GroundSpeed);
        allAttribs.add(Heading);
        allAttribs.add(CompassHeading);
        allAttribs.add(AngleNick);
        allAttribs.add(AngleRoll);
        allAttribs.add(RC_Quality);
        allAttribs.add(FCFlags);
        allAttribs.add(NCFlags);
        allAttribs.add(Errorcode);
        allAttribs.add(OperatingRadius);
        allAttribs.add(TopSpeed);
        allAttribs.add(TargetHoldTime);
        allAttribs.add(RC_RSSI);
        allAttribs.add(SetpointAltitude);
        allAttribs.add(Gas);
        allAttribs.add(Current);
        allAttribs.add(UsedCapacity);

        for (c_int c : allAttribs) {
            c.addObserver(this);
        }
    }

    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}



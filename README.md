BLEE is IoT based plug and play board through Bluetooth Low Energy. This board is mainly designed to act as a highly adaptive IoT board which can
  work in a variety of communication protocols such as WiFi, LoRa, LTE and sigfox. 


The board functions in two modes - a configure mode and a deploy mode.

Configure mode works on BLE where the pycom board acts as a GATT
server and the mobile acts as a BLE Client getting services and characteristics
 of the board.


Deploy mode requests the sensor data through the protocol chosen by the user.
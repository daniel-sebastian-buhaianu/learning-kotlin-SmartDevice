open class SmartDevice (val name: String , val category: String) {
    
    var deviceStatus = "online"
    
    constructor(name: String, category: String, statusCode: Int) : 
    	this(name, category) {
            deviceStatus = when (statusCode) {
                0 -> "offline"
                1 -> "online"
                else -> "unknown"
            }
    }
    
    open fun turnOn() {
        println("Smart Device is turned on.")
    }
    
    open fun turnOff() {
        println("Smart Device is turned off.")
    }
}

class SmartTvDevice(deviceName: String, deviceCategory: String) :
	SmartDevice(name = deviceName, category = deviceCategory) {
    
	var speakerVolume = 25
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }
    
    var channelNumber = 1
        set(value) {
            if (value in 0..200) {
                field = value
            }
    	}
    
    override fun turnOn() {
        deviceStatus = "on"
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber."
        )
    }

    override fun turnOff() {
        deviceStatus = "off"
        println("$name turned off")
    }

	fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }
    
    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber")
    }
    
}
    
class SmartLightDevice(deviceName: String, deviceCategory: String) :
	SmartDevice(name = deviceName, category = deviceCategory) {
	
	var brightnessLevel = 0
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }
        
    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel")
    }

    override fun turnOn() {
        deviceStatus = "on"
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel")
    }

    override fun turnOff() {
        deviceStatus = "off"
        brightnessLevel = 0
        println("$name turned off.")
    }
}
    
class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice,
) {
    
    fun turnOnTv() {
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        smartLightDevice.turnOff()
    }

    fun increaseLightBrigthness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
    var myTv = SmartTvDevice("Samsung TV", "Entertainment")
    var myLamp = SmartLightDevice("Nexus Light", "Reading")

    myTv.turnOn()
    myLamp.turnOn()

    myTv.increaseSpeakerVolume()
    myLamp.increaseBrightness()


    myLamp.turnOff()
    myTv.turnOff()
}
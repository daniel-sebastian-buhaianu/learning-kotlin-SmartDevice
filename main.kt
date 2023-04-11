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
    
    fun turnOn() {
        println("Smart Device is turned on.")
    }
    
    fun turnOff() {
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
        
    fun increaseBrigthtness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel")
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
        smartLightDevice.increaseBrigthtness()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
    
}
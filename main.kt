import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice (val name: String , val category: String) {
    
    var deviceStatus = "online"
        protected set

    open var deviceType = "unknown"
    
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
    
    override var deviceType = "Smart TV"

    private var speakerVolume by RangeRegulator(initialValue = 25, minValue = 0, maxValue = 100)

    private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)
    
    
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

    override var deviceType = "Smart Light"
	
	private var brightnessLevel by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)
        
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
    
    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrigthness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevices() {
        deviceTurnOnCount = 0
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
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
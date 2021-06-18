import model.LawnMower
import org.apache.logging.log4j.LogManager

class Simulator(private val height: Int, val width: Int, val lawnMowers: List<LawnMower>) {

    private val logger = LogManager.getLogger()

    fun initSimulator() {
      logger.info(this.lawnMowers.size)
    }

}
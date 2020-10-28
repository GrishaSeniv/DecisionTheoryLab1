package ru.s03.game.test


import org.junit.jupiter.api.Test
import ru.s03.game.GameMatrix
import ru.s03.game.ICriteria
import ru.s03.game.ProbabilityGameMatrix
import ru.s03.game.ru.s03.game.criteria.*

internal class CriteriaTest {

    private fun matrix(): GameMatrix {
        val alternativeNames: List<String> = listOf("Продовжити роботу в звичному режимі", "Активувати рекламну діяльність", "Активувати рекламу і знизити ціну")
        val natureStateNames: List<String> = listOf("Конкуренція на тому ж рівні", "Конкуренція трішки посилилася", "Конкуренція різко посилилася")
        val matrix: List<List<Double>> = listOf(
                listOf(100.0, 80.0, 50.0),
                listOf(70.0, 90.0, 60.0),
                listOf(60.0, 70.0, 70.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        return gm;
    }

    private fun probabilityMatrix(): ProbabilityGameMatrix {
        val alternativeNames: List<String> = listOf("Продовжити роботу в звичному режимі", "Активувати рекламну діяльність", "Активувати рекламу і знизити ціну")
        val natureStateNames: List<String> = listOf("Конкуренція на тому ж рівні", "Конкуренція трішки посилилася", "Конкуренція різко посилилася")
        val matrix: List<List<Double>> = listOf(
                listOf(100.0, 80.0, 50.0),
                listOf(70.0, 90.0, 60.0),
                listOf(60.0, 70.0, 70.0)
        )
        val probabilities: List<Double> = listOf(0.5, 0.35, 0.15)
        val gm = ProbabilityGameMatrix(matrix, alternativeNames, natureStateNames, probabilities)
        return gm;
    }

    private fun testCriteria(gameMatrix: GameMatrix, criteria: ICriteria, name: String){
        println(gameMatrix.toString())
        val optimum = criteria.optimum()
        println("$name. Оптимальна стратегія: ")
        optimum.forEach { o -> println(o.toString()) }
    }

    @Test
    fun testWaldCriteria() {
        val matrix = matrix();
        val criteria = WaldCriteria(matrix)
        testCriteria(matrix, criteria, "Критерій Вальда")
    }

    @Test
    fun testLaplaceCriteria(){
        val matrix = matrix();
        val criteria = LaplaceCriteria(matrix)
        testCriteria(matrix, criteria, "Критерій Лапласа")
    }

    @Test
    fun testHurwitzCriteria(){
        val matrix = matrix();
        val opt = 0.6
        val criteria = HurwitzCriteria(matrix, opt)
        testCriteria(matrix, criteria, "Критерій Гурвіца")
    }

    @Test
    fun testBayesCriteria(){
        val matrix = probabilityMatrix();
        val criteria = BayesCriteria(matrix)
        testCriteria(matrix, criteria, "Критерій Байєса-Лапласа")
    }




}
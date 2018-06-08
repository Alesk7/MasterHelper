package alesk.com.masterhelper.domain

import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.data.entities.Material

fun calculateJobsSum(jobs: List<Job>): Double {
    var sum = 0.0
    jobs.forEach { sum += it.priceSum }
    return sum
}

fun calculateMaterialsSum(materials: List<Material>): Double {
    var sum = 0.0
    materials.forEach { sum += it.priceSum }
    return sum
}

fun calculateEstimateSum(jobs: List<Job>, materials: List<Material>): Double {
    return calculateJobsSum(jobs) + calculateMaterialsSum(materials)
}
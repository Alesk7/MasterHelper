package alesk.com.masterhelper.presentation.main.projects.completedProjects

import alesk.com.masterhelper.data.entities.Project

interface CompletedProjectsView {
    fun setProjectsList(projectsList: List<Project>)
}
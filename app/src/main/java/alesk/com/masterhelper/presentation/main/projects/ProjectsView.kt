package alesk.com.masterhelper.presentation.main.projects

import alesk.com.masterhelper.data.entities.Project

interface ProjectsView {
    fun setProjectsList(projectsList: List<Project>)
}
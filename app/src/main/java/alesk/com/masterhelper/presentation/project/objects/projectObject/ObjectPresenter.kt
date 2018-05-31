package alesk.com.masterhelper.presentation.project.objects.projectObject

import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ObjectModel
import javax.inject.Inject

class ObjectPresenter @Inject constructor(): BasePresenter<ObjectView, ObjectRouter>() {

    lateinit var objectModel: ObjectModel

    override fun onStart() {
        objectModel = view!!.getObjectModel()
        view?.setObjectName(objectModel.name)
    }

}
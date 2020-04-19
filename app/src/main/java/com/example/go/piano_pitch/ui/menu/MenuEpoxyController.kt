package com.example.go.piano_pitch.ui.menu

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.example.go.piano_pitch.ItemPitchTypeBindingModel_
import com.example.go.piano_pitch.R
import com.example.go.piano_pitch.logic.PitchType
import javax.inject.Inject

class MenuEpoxyController @Inject constructor(
    private val listener: OnClickPitchTypeListener
) : EpoxyController() {

    private var pitchTypes: List<com.example.go.piano_pitch.data.database.entity.PitchType> =
        emptyList()

    @ExperimentalStdlibApi
    private val data: Array<PitchType> = PitchType.values()

    @ExperimentalStdlibApi
    override fun buildModels() {
        carousel {
            id("pitchTypes")
            numViewsToShowOnScreen(3.1f)
            paddingRes(R.dimen.margin_mid)
            hasFixedSize(true)

            models(
                data.mapIndexed { index, it ->
                    val clearedTimes =
                        pitchTypes.singleOrNull { v -> v.id == it.id }?.clearedTimes ?: 0
                    ItemPitchTypeBindingModel_()
                        .id(index)
                        .pitchType(it)
                        .clearedTimes(clearedTimes)
                        .onBind { _, view, _ ->
                            view.dataBinding.root.setOnClickListener { _ ->
                                listener.onClick(it)
                            }
                        }
                }
            )
        }
    }

    fun setPitchTypes(pitchTypes: List<com.example.go.piano_pitch.data.database.entity.PitchType>) {
        this.pitchTypes = pitchTypes
        requestModelBuild()
    }

    interface OnClickPitchTypeListener {
        @ExperimentalStdlibApi
        fun onClick(pitchType: PitchType)
    }
}
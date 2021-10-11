package com.example.belltakehome.models

data class ScreenDetailsResponse(
    var alias: Alias? = null,
    var title: String? = null,
    var summary: String? = null,
    var style: String? = null,
    var alternativeTitleLink: String? = null,
    var displayRotatorTitle: Boolean? = null,
    var displayCollectionCount: Boolean? = null,
    var linearCollectionId: String? = null,
    var videoStreams: List<String>? = null,
    var upsellPackage: String? = null,
    var aliases: List<String>? = null,
    var maxItems: Int? = null,
    var collectionType: String? = null,
    var type: String? = null,
    var itemCount: Int? = null,
    var axisCollectionId: String? = null,
    var mixedCollectionAlias: String? = null)

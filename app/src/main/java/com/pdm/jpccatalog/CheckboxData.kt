package com.pdm.jpccatalog

data class CheckboxData (val title: String, var selected:Boolean = false, var onCheckedChange:(Boolean) -> Unit) {
}
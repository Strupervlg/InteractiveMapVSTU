package com.example.interactivemap.ui.screens

import androidx.compose.ui.Modifier
import com.example.interactivemap.viewmodels.*
import ovh.plrapps.mapcompose.ui.MapUI
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.interactivemap.ui.theme.VSTUBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import ovh.plrapps.mapcompose.api.onMarkerClick

var sizeSpaceBetweenButtons: Float = 1.5F

@ExperimentalAnimationApi
@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    val floors = listOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    val selectedOption = remember { mutableStateOf(floors[0]) }
    val r = remember { mutableStateOf("") }

    if (selectedOption.value == 9) {
        val floor: NinthFloor = viewModel()
        MapUI(modifier, state = floor.state)
        floor.state.onMarkerClick { id, x, y ->
            if (r.value == id) {
                r.value = ""
            } else {
                r.value = id
            }
        }
    }

    if (selectedOption.value == 6) {
        val floor: SixthFloor = viewModel()
        MapUI(modifier, state = floor.state)
        floor.state.onMarkerClick { id, x, y ->
            if (r.value == id) {
                r.value = ""
            } else {
                r.value = id
            }
        }
    }

    Column(modifier = Modifier.padding(10.dp)) {
        Spacer(modifier = Modifier.height(70.dp))
        floors.forEach { floor ->
            val selected = selectedOption.value == floor
            Button(onClick = { selectedOption.value = floor }, modifier =
            Modifier
                .selectable(
                    selected = selected,
                    onClick = { }
                )
                .border(
                    width = if (selected) {2.dp} else {0.dp},
                    color = Color.Black
                ),
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = if (selected) {
                        MaterialTheme.colors.secondary
                    } else {
                        MaterialTheme.colors.primary
                    },
                    contentColor = Color.Black
                )
            ) {
                Text(text = floor.toString())
            }
            Spacer(modifier = Modifier.height(sizeSpaceBetweenButtons.dp))
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Button(onClick = {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message = "Данная функция временно не поддерживается")
            }
        }
        )
        { Text("Навигатор") }

        Spacer(modifier = Modifier.height(sizeSpaceBetweenButtons.dp))
    }

    val state: SearchState = rememberSearchState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        SearchBar(
            query = state.query,
            onQueryChange = { state.query = it },
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue("") },
            onBack = { state.query = TextFieldValue("") },
            searching = state.searching,
            focused = state.focused,
            modifier = modifier
        )

        LaunchedEffect(state.query.text) {
            state.searching = true
            delay(100)
            state.searching = false
        }
    }
    cabinetDescription(r)
}

val suggestionList = listOf(
    SuggestionModel("Деканат"),
    SuggestionModel("Кафедра ПОАС"),
    SuggestionModel("Кафедра высшей математики")
)

class SearchViewModel : ViewModel() {

    var selectedPage: Int = 0

    lateinit var componentCabinetList: List<CabinetModel>
    lateinit var layoutCabinets: List<CabinetModel>

    val cabinetList = mutableListOf<List<CabinetModel>>()

    private val _suggestionState = MutableStateFlow<List<SuggestionModel>>(suggestionList)

    val suggestionState: SharedFlow<List<SuggestionModel>>
        get() = _suggestionState


    fun addSuggestion(suggestionModel: SuggestionModel) {

    }

    fun getCabinets(query: String): List<CabinetModel> {

        val filteredList = linkedSetOf<CabinetModel>()

        cabinetList.forEach { list: List<CabinetModel> ->

            list.forEach { cabinetModel ->

                if (cabinetModel.description.contains(query, ignoreCase = true)) {
                    filteredList.add(cabinetModel)
                }

                cabinetModel.tags.forEach {
                    if (it.contains(query, ignoreCase = true)) {
                        filteredList.add(cabinetModel)
                    }
                }
            }
        }

//        println("🤖 ViewModel Query: $query, filteredList: ${filteredList.size}")

        return if (query.isEmpty()) cabinetList[selectedPage] else filteredList.toList()
    }
}

data class SuggestionModel(val tag: String) {
    val id = tag.hashCode()
}

data class CabinetModel(
    val title: String,
    val action: @Composable (() -> Unit)? = null,
    val description: String,
    val tags: List<String> = listOf(),
    val tagColor: Color = Color(0xff00BCD4),
    var expanded: Boolean = false
)

enum class SearchDisplay {
    InitialResults, Suggestions, Results, NoResults
}

@Stable
class SearchState(
    query: TextFieldValue,
    focused: Boolean,
    searching: Boolean,
    suggestions: List<SuggestionModel>,
    searchResults: List<CabinetModel>
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)
    var searching by mutableStateOf(searching)
    var suggestions by mutableStateOf(suggestions)
    var searchResults by mutableStateOf(searchResults)

    val searchDisplay: SearchDisplay
        get() = when {
            !focused && query.text.isEmpty() -> SearchDisplay.InitialResults
            focused && query.text.isEmpty() -> SearchDisplay.Suggestions
            searchResults.isEmpty() -> SearchDisplay.NoResults
            else -> SearchDisplay.Results
        }

    override fun toString(): String {
        return "🚀 State query: $query, focused: $focused, searching: $searching " +
                "suggestions: ${suggestions.size}, " +
                "searchResults: ${searchResults.size}, " +
                " searchDisplay: $searchDisplay"

    }
}

@Composable
fun rememberSearchState(
    query: TextFieldValue = TextFieldValue(""),
    focused: Boolean = false,
    searching: Boolean = false,
    suggestions: List<SuggestionModel> = emptyList(),
    searchResults: List<CabinetModel> = emptyList()
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused,
            searching = searching,
            suggestions = suggestions,
            searchResults = searchResults
        )
    }
}

@Composable
private fun SearchHint(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)

    ) {
        Text(
            color = Color(0xff757575),
            text = "Search a Tag or Description",
        )
    }
}

@Composable
fun SearchTextField(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    searching: Boolean,
    focused: Boolean,
    modifier: Modifier = Modifier
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = modifier
            .then(
                Modifier
                    .height(56.dp)
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = if (!focused) 16.dp else 0.dp,
                        end = 16.dp
                    )
            ),
        color = Color(0xffF5F5F5),
        shape = RoundedCornerShape(percent = 50),
    ) {

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = modifier
            ) {

                if (query.text.isEmpty()) {
                    SearchHint(modifier.padding(start = 24.dp, end = 8.dp))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
//                    TextField(modifier = Modifier
//                        .fillMaxWidth(.9f)
//                        .padding(8.dp)
//                        .background(color = Color.White)
//                        ,
//                        value = query,
//                        onValueChange = onQueryChange,
//                        label = { Text("Поиск") },
//                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//                        keyboardActions = KeyboardActions(
//                            onDone = {
//                                // keyboardController?.hide()
//                                focusManager.clearFocus()
//                            }
//                        ),leadingIcon = {
//                            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
//                        },
//                        textStyle = TextStyle(color = MaterialTheme.colors.onSurface)
//                    )
                    BasicTextField(
                        value = query,
                        onValueChange = onQueryChange,
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .onFocusChanged {
                                onSearchFocusChange(it.isFocused)
                            }
                            .focusRequester(focusRequester)
                            .padding(top = 9.dp, bottom = 8.dp, start = 24.dp, end = 8.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                // keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        )
                    )

                    when {
                        searching -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp)
                                    .size(36.dp)
                            )
                        }
                        query.text.isNotEmpty() -> {
                            IconButton(onClick = onClearQuery) {
                                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }

    }
}

@ExperimentalAnimationApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearchFocusChange: (Boolean) -> Unit,
    onClearQuery: () -> Unit,
    onBack: () -> Unit,
    searching: Boolean,
    focused: Boolean,
    modifier: Modifier = Modifier
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(visible = focused) {
            // Back button
            IconButton(
                modifier = Modifier.padding(start = 2.dp),
                onClick = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                    onBack()
                }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }

        SearchTextField(
            query,
            onQueryChange,
            onSearchFocusChange,
            onClearQuery,
            searching,
            focused,
            modifier.weight(1f)
        )
    }
}


@Composable
@ExperimentalAnimationApi
fun cabinetDescription(idCabindet: MutableState<String>) {
    if (!idCabindet.value.isEmpty()) {
        var visibleDescription = remember { mutableStateOf(true) }
        AnimatedVisibility(visible = visibleDescription.value) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 80.dp, end = 2.dp, bottom = 2.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Card(
                    shape = RoundedCornerShape(15.dp),
                    backgroundColor = Color.White,
                    border = BorderStroke(width = 3.5.dp, color = VSTUBlue),
                    modifier = Modifier
                        .size(310.dp, 240.dp)
                    ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = idCabindet.value,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp, top = 60.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(cabinetToDiscription(idCabindet.value))
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) {
                        IconButton(
                            modifier = Modifier.padding(start = 2.dp),
                            onClick = {
                                visibleDescription.value = false
                                idCabindet.value = ""
                            }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

fun cabinetToDiscription(cabinet: String): String {
    if (cabinet == "901") return "Лекционный класс"
    else if (cabinet == "902а") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "902б") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "902в") return "Аудитория для проведения лабораторных работ и практик"
    else if (cabinet == "903") return "Аудитория V.I.S.D.O.M. laboratory"
    else if (cabinet == "904") return "Преподавательская"
    else if (cabinet == "905") return "Преподавательская"
    else if (cabinet == "906") return "Аудитория для проведения практик"
    else if (cabinet == "907") return "Аудитория для проведения практик"
    else if (cabinet == "908") return "Аудитория для проведения практик"
    else return ""
}

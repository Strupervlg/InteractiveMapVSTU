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
<<<<<<< Updated upstream

var sizeSpaceBetweenButtons: Float = 1.5F

=======
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import ovh.plrapps.mapcompose.api.centerOnMarker
import java.lang.NumberFormatException
import androidx.lifecycle.viewModelScope

var sizeSpaceBetweenButtons: Float = 1.5F


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
>>>>>>> Stashed changes
@ExperimentalAnimationApi
@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    val floors = listOf(14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
    val selectedOption = remember { mutableStateOf(floors[0]) }
    val centerOn = remember { mutableStateOf("") }
    val r = remember { mutableStateOf("") }

    if (selectedOption.value == 9) {
        r.value = ""
        val floor: NinthFloor = viewModel()
        MapUI(modifier, state = floor.state)
        floor.state.onMarkerClick { id, x, y ->
            if (r.value == id) {
                r.value = ""
            } else {
                r.value = id
            }
        }
        if(!centerOn.value.isEmpty()) {
            floor.onCenter(centerOn.value)
            centerOn.value = ""
        }
    }
    if (selectedOption.value == 8) {
        r.value = ""
        val floor: EighthFloor = viewModel()
        MapUI(modifier, state = floor.state)
        floor.state.onMarkerClick { id, x, y ->
            if (r.value == id) {
                r.value = ""
            } else {
                r.value = id
            }
        }
        if(!centerOn.value.isEmpty()) {
            floor.onCenter(centerOn.value)
            centerOn.value = ""
        }
    }
    if (selectedOption.value == 6) {
        r.value = ""
        val floor: SixthFloor = viewModel()
        MapUI(modifier, state = floor.state)
        floor.state.onMarkerClick { id, x, y ->
            if (r.value == id) {
                r.value = ""
            } else {
                r.value = id
            }
        }
        if(!centerOn.value.isEmpty()) {
            floor.onCenter(centerOn.value)
            centerOn.value = ""
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
                ),
                border = BorderStroke(width = if (selected) {2.dp} else {0.dp}, color = Color.Black),
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

<<<<<<< Updated upstream
    val state: SearchState = rememberSearchState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
=======
    val mainViewModel: HomeViewModel = viewModel()
    mainViewModel.cabinetList = createComponentTutorialList()

    HomeScreen(
        viewModel = mainViewModel,
        selectedOption = selectedOption,
        onCenter = centerOn
    )
    
    cabinetDescription(r)
}
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
class SearchViewModel : ViewModel() {
=======
data class CabinetSectionModel(
    val title: String,
    val action: @Composable (() -> Unit)? = null,
    val description: String,
    val tags: List<String> = listOf(),
    val tagColor: Color = Color(0xff00BCD4),
    var expanded: Boolean = false
)

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun createComponentTutorialList(): List<CabinetSectionModel> {
    val tutorial1_1 = CabinetSectionModel(
        title = "901",
        action = {

        },
        description = "Create Rows, Columns and Box, how to add modifiers to " +
                "composables. Set padding, margin, alignment other properties of composables.",
        tags = listOf(
            "Suck"
        )
    )

    val tutorial1_2 = CabinetSectionModel(
        title = "801",
        action = {

        },
        description = "Create Rows, Columns and Box, how to add modifiers to " +
                "composables. Set padding, margin, alignment other properties of composables.",
        tags = listOf(
            "my"
        )
    )

    val tutorial1_3 = CabinetSectionModel(
        title = "601",
        action = {

        },
        description = "Create Rows, Columns and Box, how to add modifiers to " +
                "composables. Set padding, margin, alignment other properties of composables.",
        tags = listOf(
            "duck"
        )
    )

    return listOf(
        tutorial1_1,
        tutorial1_2,
        tutorial1_3
    )
}

//@ExperimentalAnimationApi
//@OptIn(ExperimentalMaterialApi::class)
//@ExperimentalFoundationApi
//@ExperimentalComposeUiApi
//@Composable
//fun createLayoutTutorialList(): List<TutorialSectionModel> {
//
//    val tutorial3_1 = TutorialSectionModel(
//        title = stringResource(R.string.title3_1),
//        description = "Create custom modifiers using layout, Measurable, Constraint, Placeable," +
//                " and LayoutModifier.",
//        action = {
//            Tutorial3_1Screen()
//        },
//        tags = listOf(
//            TAG_COMPOSE,
//            TAG_CUSTOM_MODIFIER,
//            TAG_MEASURABLE,
//            TAG_CONSTRAINT,
//            TAG_PLACEABLE,
//            TAG_LAYOUT_MODIFIER
//        ),
//        tagColor = Color(0xffFFEB3B)
//    )
//}
//
//@Composable
//fun createStateTutorialList(): List<TutorialSectionModel> {
//
//    val tutorial4_1 = TutorialSectionModel(
//        title = stringResource(R.string.title_4_1),
//        description = "This tutorial shows how recomposition happens for flat or hierarchical " +
//                "designs when Composables are in separate functions or stacked together.",
//        action = {
//            Tutorial4_1Screen()
//        },
//        tags = listOf(
//            TAG_COMPOSE,
//            TAG_RECOMPOSITION,
//            TAG_STATE
//        ),
//        tagColor = Color(0xffE91E63)
//    )
//}

class HomeViewModel : ViewModel() {
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
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
=======
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    state: SearchState<CabinetSectionModel, SuggestionModel> = rememberSearchState(),
    selectedOption: MutableState<Int>,
    onCenter: MutableState<String>
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
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
=======
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        when (state.searchDisplay) {
            SearchDisplay.InitialResults -> {
            }
            SearchDisplay.NoResults -> {
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No Results!", fontSize = 24.sp, color = Color(0xffDD2C00))
>>>>>>> Stashed changes
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
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

<<<<<<< Updated upstream
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
=======

            SearchDisplay.Results -> {
                Column(modifier = Modifier.padding(10.dp)) {
                    Spacer(modifier = Modifier.height(70.dp))
                    state.searchResults.forEach { res ->
                        Box(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                selectedOption.value = res.title.get(0).digitToInt()
                                onCenter.value = res.title
                                focusManager.clearFocus()
                                keyboardController?.hide()
                                state.query = TextFieldValue("")
                            }) {
                                Text(text = res.title)
                            }
                        }
                        Spacer(modifier = Modifier.height(sizeSpaceBetweenButtons.dp))
                    }
                }
>>>>>>> Stashed changes
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

    else if (cabinet == "801") return "Аудитория для проведения практик"
    else if (cabinet == "802") return "Лекционный класс"
    else if (cabinet == "803") return "Преподавательская"
    else if (cabinet == "804") return "Аудитория для проведения практик"
    else if (cabinet == "805") return "Аудитория для проведения практик"
    else if (cabinet == "806") return "Аудитория для проведения практик"
    else if (cabinet == "807") return "Преподавательская"

    else if (cabinet == "601") return "Аудитория для проведения практик"
    else if (cabinet == "602") return "Аудитория для проведения практик"
    else if (cabinet == "603") return "Аудитория для проведения практик"
    else if (cabinet == "604") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "605") return "Аудитория для проведения лабораторных работ"
    else if (cabinet == "606") return "Преподавательская"
    else if (cabinet == "607") return "Преподавательская"
    else if (cabinet == "608") return "Преподавательская"
    else if (cabinet == "609") return "Преподавательская"
    else if (cabinet == "610") return "Аудитория для проведения практик"
    else if (cabinet == "611") return "Аудитория для проведения практик"
    else if (cabinet == "612") return "Аудитория для проведения лабораторных работ"
    else return ""
}

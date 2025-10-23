# A simple implementation of bottom navigation bar

## Step 1: Adding the dependency
the dependency used here is 
```
implementation("androidx.navigation:navigation-compose:2.8.4")

```

## Step 2: Creation and listing of the navigation items that hold the compinents of each item in navbar
```
val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = Screen.Home.route
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = Screen.Profile.route
        ),
        NavigationItem(
            title = "Cart",
            icon = Icons.Default.ShoppingCart,
            route = Screen.Cart.route
        ),
        NavigationItem(
            title = "Setting",
            icon = Icons.Default.Settings,
            route = Screen.Setting.route
        )
    )
    ```

    ```
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

```

## Step 3: Create a sealed class to define the routes for each item
```
sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Profile: Screen("profile_screen")
    object Cart: Screen("cart_screen")
    object Setting: Screen("setting_screen")
}
```

## Step 4: Creating the screens
```
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Home Screen")
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile Screen")
    }
}
@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Cart Screen")
    }
}

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Setting Screen")
    }
}
```

## Step 5: Implementing the bottom navigation bar
We will use the Navigation Bar composable from android

```
val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    
NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
```

## Step 6: Defining a Navigation Graph
Navigation Graph defines how you move between screens (composables) in your app — and how data flows between them. 
Think of it as a map of all your app’s destinations (screens) and the paths that connect them.

    ```
    val graph =
                navController.createGraph(startDestination = Screen.Home.route) {
                    composable(route = Screen.Cart.route) {
                        CartScreen()
                    }
                    composable(route = Screen.Setting.route) {
                        SettingScreen()
                    }
                    composable(route = Screen.Home.route) {
                        HomeScreen()
                    }
                    composable(route = Screen.Profile.route) {
                        ProfileScreen()
                    }
                }
    ```

  ## Step 7: Setup a Navigation host
  This is the container that hosts your app’s navigation destinations (your screens).

  ```
  NavHost(
                navController = navController,
                graph = graph,
                modifier = Modifier.padding(innerPadding)
            )
  ```

  

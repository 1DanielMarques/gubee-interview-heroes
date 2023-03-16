package br.com.gubee.interview.core.features.hero;

/*@WebMvcTest(HeroResource.class)
class HeroResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HeroService heroService;

    private HeroDTO heroDTO;

    @BeforeEach
    public void initTest() {
//        when(heroService.create(any())).thenReturn(createHeroRequest());

    }

    @BeforeEach
    public void heroRequestWithId() {
        heroDTO = createHeroRequest();
        heroDTO.setId(UUID.randomUUID());
    }


    @Test
    @DisplayName("Should Create Hero with all required arguments")
    void shouldCreateAHeroWithAllRequiredArguments() throws Exception {
        //given
        // Convert the hero request into a string JSON format stub.
        final String body = objectMapper.writeValueAsString(createHeroRequest());

        //when
        final ResultActions resultActions = mockMvc.perform(post("/api/v1/heroes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        //then
        resultActions.andExpect(status().isCreated()).andExpect(header().exists("Location"));
        verify(heroService, times(1)).create(any());
    }

    private HeroDTO createHeroRequest() {
        return HeroDTO.builder()
                .name("Batman")
                .agility(5)
                .dexterity(8)
                .strength(6)
                .intelligence(10)
                .race(Race.HUMAN)
                .build();
    }

    @Test
    @DisplayName("Should return a list of all Heroes with its attributes")
    void shouldReturnListOfAllHeroesWithItsAttributes() throws Exception {
        when(heroService.findAll()).thenReturn(List.of(heroDTO));

        final ResultActions resultActions = mockMvc.perform(get("/api/v1/heroes")).andExpect(status().isOk());

        var json = String.format("[ { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ]", heroDTO.getId().toString());

        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    @DisplayName("Should find a Hero and its attributes by id")
    void shouldFindHeroByIdWithItsAttributes() throws Exception {
        when(heroService.findById(any())).thenReturn(this.heroDTO);

        var url = String.format("/api/v1/heroes/id/%s", heroDTO.getId().toString());

        final ResultActions resultActions = mockMvc.perform(get(url)).andExpect(status().isOk());

        var json = String.format(" { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ", heroDTO.getId().toString());

        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    @DisplayName("Should find a hero and its attributes by name")
    void shouldFindHeroByNameWithItsAttributes() throws Exception {
        when(heroService.findByName(any())).thenReturn(this.heroDTO);

        var url = String.format("/api/v1/heroes/name/%s", this.heroDTO.getName());

        final ResultActions resultActions = mockMvc.perform(get(url)).andExpect(status().isOk());

        var json = String.format(" { \"id\": \"%s\", \"name\": \"Batman\", \"race\": \"HUMAN\", \"strength\": 6, \"agility\": 5, \"dexterity\": 8, \"intelligence\": 10 } ", heroDTO.getId().toString());
        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    @DisplayName("Should return ComparedHeroes with right attributes")
    void shouldReturnComparedHeroes() throws Exception {
        UUID firstHeroId = UUID.randomUUID();
        UUID secondHeroId = UUID.randomUUID();
        ComparedHeroes comparedHeroes = ComparedHeroes.builder()
                .firstId(firstHeroId)
                .firstAgility(-5)
                .firstDexterity(8)
                .firstStrength(-6)
                .firstIntelligence(10)
                .secondId(secondHeroId)
                .secondAgility(7)
                .secondDexterity(-6)
                .secondStrength(8)
                .secondIntelligence(-6)
                .build();
        when(heroService.compareHeroes(any(), any())).thenReturn(comparedHeroes);

        var url = String.format("/api/v1/heroes/compare/%s/with/%s", this.heroDTO.getName(), "Superman");

        final ResultActions resultActions = mockMvc.perform(get(url)).andExpect(status().isOk());

        var json = String.format("  {\n" +
                "            \"first_id\": \"%s\",\n" +
                "                \"first_strength\": -6,\n" +
                "                \"first_agility\": -5,\n" +
                "                \"first_dexterity\": 8,\n" +
                "                \"first_intelligence\": 10,\n" +
                "                \"second_id\": \"%s\",\n" +
                "                \"second_strength\": 8,\n" +
                "                \"second_agility\": 7,\n" +
                "                \"second_dexterity\": -6,\n" +
                "                \"second_intelligence\": -6\n" +
                "        } ", firstHeroId, secondHeroId);
        resultActions.andExpect(status().isOk()).andExpect(content().json(json));
    }

    @Test
    void shouldUpdateHeroById() {

    }

    @Test
    void shouldDeleteHeroById() {

    }


}*/
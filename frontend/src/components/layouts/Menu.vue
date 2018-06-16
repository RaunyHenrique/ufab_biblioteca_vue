<template>

  <b-navbar toggleable="md" type="dark" variant="dark">

    <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

    <b-navbar-brand href="#">
      <img src="@/assets/logo.png" class="d-inline-block align-top" alt="BV" height="30" width="30">
      Biblioteca - UFAB
    </b-navbar-brand>

    <b-collapse is-nav id="nav_collapse">

      <b-navbar-nav>

        <b-nav-item to="/">Home</b-nav-item>
        <b-nav-item to="/alunos">Alunos</b-nav-item>
        <b-nav-item to="/cursos">Cursos</b-nav-item>
        <b-nav-item to="/funcionarios">Funcionarios</b-nav-item>

        <b-nav-item-dropdown text="Acervo" right>
          <b-dropdown-header>Itens do acervo</b-dropdown-header>
          <b-dropdown-item to="/livros">Livros</b-dropdown-item>
          <b-dropdown-item to="/revistas">Revistas</b-dropdown-item>
          <b-dropdown-item to="/tccs">TCC's</b-dropdown-item>
          <b-dropdown-item to="/jornais">Jornais</b-dropdown-item>
          <b-dropdown-item to="/anais">Anais</b-dropdown-item>
          <b-dropdown-item to="/midias">Midias Eletronicas</b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-header>Outros</b-dropdown-header>
          <b-dropdown-item to="/autores">Autores</b-dropdown-item>
          <b-dropdown-item to="/orientadores">Orientadores</b-dropdown-item>
          <b-dropdown-item to="/editoras">Editoras</b-dropdown-item>
          <b-dropdown-item to="/areas-de-conhecimento">Áreas de conhecimento</b-dropdown-item>
          <b-dropdown-item to="/temas">Temas</b-dropdown-item>
        </b-nav-item-dropdown>

      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">

        <!--<b-nav-form>-->
          <!--<b-form-input size="sm" class="mr-sm-2" type="text" placeholder="Buscar..."/>-->
          <!--<b-button size="sm" class="my-2 my-sm-0" variant="primary" type="submit">ok</b-button>-->
        <!--</b-nav-form>-->

        <b-nav-item-dropdown right>
          <!-- Using button-content slot -->
          <template slot="button-content">
            <em>Usuário</em>
          </template>
          <b-dropdown-item to="/logout">Logout</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

    </b-collapse>
  </b-navbar>

</template>

<script>
  export default {
    name: "Menu"
  }
</script>

<style scoped>

</style>

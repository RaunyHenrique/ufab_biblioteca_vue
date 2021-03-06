<template>

  <div>

    <!--Title-->
    <b-row>

      <b-col>

        <h1 id="title-page" class="text-left">{{title}}</h1>
        <b-button @click.stop="showModal('Cadastrar', null, null, $event.target)" id="btn-cadastrar" variant="primary" style="display: inline-block;">Cadastrar livro</b-button>

      </b-col>

    </b-row>

    <hr>

    <!-- User Interface controls -->
    <b-row id="ui-table">

      <b-col md="6" class="my-1" id="per-page-select">
        <b-form-group horizontal label="Por página" class="mb-0">
          <b-form-select :options="pageOptions" v-model="perPage" />
        </b-form-group>
      </b-col>

      <b-col md="6" class="my-1">
        <b-form-group horizontal label="Filtrar" class="mb-0">
          <b-input-group>
            <b-form-input v-model="filter" placeholder="Buscar..." />
            <b-input-group-append>
              <b-btn variant="primary" :disabled="!filter" @click="filter = ''">Apagar</b-btn>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
      </b-col>

    </b-row>

    <!-- Main table element -->
    <b-table show-empty
             stacked="md"
             :items="items"
             :fields="table_fields"
             :current-page="currentPage"
             :per-page="perPage"
             :filter="filter"
             :sort-by.sync="sortBy"
             :sort-desc.sync="sortDesc"
             :sort-direction="sortDirection"
             @filtered="onFiltered"
             :empty-filtered-text="'Nenhum resultado encontrado.'"
             :empty-text="'Não há nenhum registro a ser mostrado.'"
             id="table-listar"
    >

      <template slot="data" slot-scope="row">{{ row.value | moment("YYYY") }}</template>

      <template slot="autores" slot-scope="row">

        <b-badge pill variant="primary" v-for="(value, key) in row.value" :key="key">{{ value.nome }}</b-badge>

      </template>

      <template slot="actions" slot-scope="row">

        <b-button variant="warning" size="sm" @click.stop="showModal('Editar', row.item, row.index, $event.target)" class="mr-1">
          Editar
        </b-button>

        <b-button variant="danger" size="sm" @click.stop="showDeleteModal('Deletar', row.item, row.index, $event.target)">
          Deletar
        </b-button>

      </template>

    </b-table>

    <!-- Pagination -->
    <b-row>
      <b-col md="6" class="my-1">
        <b-pagination :total-rows="totalRows" :per-page="perPage" v-model="currentPage" class="my-0" />
      </b-col>
    </b-row>

    <!-- modal-add-edit -->
    <form-modal :error="error" :list-erros="listErros" :modal_id="'modal-add-edit'" :title="modalTitle" :form_fields="modal_form_fields" :form="form" v-on:ok_modal="submitForm">

      <template slot="campos_personalizados">

        <b-form-group label="Autores"
                      label-for="autores">

          <b-form-select id="autores"
                         name="autores"
                         :options="autores"
                         required
                         multiple
                         v-model="form.autores">
            <template slot="first">
              <option :value="null">Selecione...</option>
            </template>
          </b-form-select>

        </b-form-group>

        <b-form-group label="Editora"
                      label-for="editora">

          <b-form-select id="editora"
                         name="editora"
                         :options="editoras"
                         required
                         v-model="form.editora">
            <template slot="first">
              <option :value="null">Selecione...</option>
            </template>
          </b-form-select>

        </b-form-group>

        <b-form-group label="Tema"
                      label-for="tema">

          <b-form-select id="tema"
                         name="tema"
                         :options="temas"
                         required
                         v-model="form.tema">
            <template slot="first">
              <option :value="null">Selecione...</option>
            </template>
          </b-form-select>

        </b-form-group>

      </template>

    </form-modal>

    <!-- modal-delete -->
    <b-modal ref="modalDelete" id="modal-delete" :title="'Deletar'" :cancel-title="'Cancelar'" @ok="handleDelete">

      <h4>Deseja realmente deletar este item?</h4>

    </b-modal>

  </div>

</template>

<script>

  import FormModal from "../../layouts/FormModal";

  export default {
    name: "Livros",
    components: { FormModal },
    data () {
      return {
        title: 'Livros',
        items: [],
        url: '/livros',
        method: '',
        modalTitle: '',
        error: false,
        listErros: [],
        modal_form_fields: [
          //OBS: os campos mapeados aqui, são mapeados no form!
          //OBS: os campos que não estão presentes aqui, são os campos personalizados!
          { key: 'id', label: 'Id', type: 'number', hidden: true},
          { key: 'titulo', label: 'Titulo', type: 'text'},
          { key: 'isbn', label: 'Isbn', type: 'number' },
          { key: 'numero_paginas', label: 'Numero de paginas', type: 'number'},
          { key: 'edicao', label: 'Edição', type: 'number'},
          { key: 'data', label: 'Ano de publicação', type: 'date'},
        ],
        table_fields: [
          { key: 'titulo', label: 'Titulo', sortable: true },
          { key: 'isbn', label: 'Isbn', sortable: true },
          { key: 'numero_paginas', label: 'Numero de paginas', sortable: true },
          { key: 'data', label: 'Ano de publicação', sortable: true },
          { key: 'autores', label: 'Autores', sortable: true },
          { key: 'actions', label: 'Ações' }
        ],
        form: {
          autores: []
        },//todos os campos do form
        currentPage: 1,
        perPage: 5,
        totalRows: 0,
        pageOptions: [ 5, 10, 15 ],
        sortBy: null,
        sortDesc: false,
        sortDirection: 'asc',
        filter: null,
        autores: [],
        editoras: [],
        temas: [],

        idToDelete: null,
        indexToDelete: null,

        indexToEdit: null,
      }
    },
    created() {
      this.getAllLivros()
      this.getAllAutores()
      this.getAllEditoras()
      this.getAllTemas()
    },
    methods: {
      getAllLivros() {

        this.$http.get(this.url)
          .then(data => {

            if (data.data.length > 0) {

              this.items = data.data

              this.totalRows = this.items.length

              //seta os campos do form de forma generica
              this.setFormFields(data.data[0])

            }

          })
          .catch((error) => {
            console.log(error)
            this.$toast.error({
              title: 'Informação',
              message: 'Ops, ocorreu algum erro',
              position: 'top right',
              progressBar: true,
              showDuration: 1000,
              hideDuration: 1000,
              timeOut: 5000
            })
          })

      },
      getAllAutores() {

        this.$http.get('/autores')
          .then(data => {

            if (data.data.length > 0) {

              var autores = []
              data.data.forEach(function (obj) {

                autores.push({text: obj['nome'], value: obj['id'], disabled: false})

              })

              this.autores = autores

            }

          })
          .catch((error) => {
            console.log(error)
            this.$toast.error({
              title: 'Informação',
              message: 'Ops, ocorreu algum erro',
              position: 'top right',
              progressBar: true,
              showDuration: 1000,
              hideDuration: 1000,
              timeOut: 5000
            })
          })

      },
      getAllEditoras() {

        this.$http.get('/editoras')
          .then(data => {

            if (data.data.length > 0) {

              var editoras = []
              data.data.forEach(function (obj) {

                editoras.push({text: obj['nome'], value: obj['id'], disabled: false})

              })

              this.editoras = editoras

            }

          })
          .catch((error) => {
            console.log(error)
            this.$toast.error({
              title: 'Informação',
              message: 'Ops, ocorreu algum erro',
              position: 'top right',
              progressBar: true,
              showDuration: 1000,
              hideDuration: 1000,
              timeOut: 5000
            })
          })

      },
      getAllTemas() {

        this.$http.get('/temas')
          .then(data => {

            if (data.data.length > 0) {

              var temas = []
              data.data.forEach(function (obj) {

                temas.push({text: obj['nome'], value: obj['id'], disabled: false})

              })

              this.temas = temas

            }

          })
          .catch((error) => {
            console.log(error)
            this.$toast.error({
              title: 'Informação',
              message: 'Ops, ocorreu algum erro',
              position: 'top right',
              progressBar: true,
              showDuration: 1000,
              hideDuration: 1000,
              timeOut: 5000
            })
          })

      },
      setFormFields(data) {//precisa desse metd. msm:?????

        var campos = {}
        Object.keys(data).forEach(function (key) {

          if (key == "autores") {
            campos[key] = []
          } else {
            campos[key] = null
          }

        })

        this.form = campos

      },
      showModal(title, item, index, event) {

        this.error = false

        if (item == null) {
          this.modalTitle = 'Cadastrar'
          this.form = {
            autores: []
          }
          this.method = 'post'
        } else {

          //seta o item da row, nos campos do form
          var campos = {}
          Object.keys(item).forEach(function (key) {
            campos[key] = item[key]
          })
          this.form = campos

          this.modalTitle = 'Editar'
          this.method = 'put'
          this.indexToEdit = index

          //seta o valor do id do curso no select de curso (pois curso vem como obj não compativel com o select)
          if (item.hasOwnProperty("autores")) {

            var autores = []
            item['autores'].forEach(function (obj) {

              autores.push(obj['id'])

            })

            this.form.autores = autores

          }

          if (item['editora'].hasOwnProperty("id")) {

            this.form.editora = item['editora']['id']

          }

          if (item['tema'].hasOwnProperty("id")) {

            this.form.tema = item['tema']['id']

          }

        }

        //abre modal
        this.$root.$emit('bv::show::modal', 'modal-add-edit', event)

      },
      showDeleteModal(title, item, index, event) {
        this.idToDelete = item['id']
        this.indexToDelete = index

        //abre modal
        this.$root.$emit('bv::show::modal', 'modal-delete', event)
      },
      onFiltered (filteredItems) {
        // Trigger pagination to update the number of buttons/pages due to filtering
        this.totalRows = filteredItems.length
        this.currentPage = 1
      },
      handleDelete () {

        if (this.idToDelete != null) {

          this.$http.delete(this.url + '/' +this.idToDelete)
            .then(data => {

              //deleta item da table
              this.items.splice(this.indexToDelete, 1);

              this.idToDelete = null
              this.indexToDelete = null

              this.$toast.success({
                title:'Informação',
                message:'Item deletado com sucesso',
                position: 'top right',
                progressBar: true,
                showDuration: 1000,
                hideDuration: 1000,
                timeOut: 5000
              })

            })
            .catch((error) => {
              console.log(error)
              this.$toast.error({
                title:'Informação',
                message:'Ops, ocorreu algum erro',
                position: 'top right',
                progressBar: true,
                showDuration: 1000,
                hideDuration: 1000,
                timeOut: 5000
              })
            })

        }

      },
      submitForm (formModal) {

        var qs = require('qs');

        if (this.method == 'post') {

          this.$http.post(this.url, qs.stringify(formModal))
            .then(data => {

              this.items.push(data.data)

              this.error = false
              this.handleSubmit()

              this.$toast.success({
                title:'Informação',
                message:'Item criado com sucesso',
                position: 'top right',
                progressBar: true,
                showDuration: 1000,
                hideDuration: 1000,
                timeOut: 5000
              })

            })
            .catch((error) => {

              this.error = true

              this.listErrosHandler(error)

            })

        } else {

          this.$http.put(this.url + '/' + formModal['id'], qs.stringify(this.form))
            .then(data => {

              this.error = false
              this.handleSubmit()

              this.$set(this.items, this.indexToEdit, data.data)

              this.indexToEdit = null

              this.$toast.success({
                title:'Informação',
                message:'Item editado com sucesso',
                position: 'top right',
                progressBar: true,
                showDuration: 1000,
                hideDuration: 1000,
                timeOut: 5000
              })

            })
            .catch((error) => {

              this.error = true

              this.listErrosHandler(error)

            })

        }

      },
      listErrosHandler (error) {

        var erros = error.response.data

        var errs = []
        erros.forEach(function (erro) {

          // if (erro['code'] != "NotNull") {

            errs.push(erro['defaultMessage'])

          // }

        })

        this.listErros = errs

      },
      resetModal () {
        this.form = {
          autores: []
        }
      },
      handleSubmit () {
        //fecha modal
        this.$root.$emit('bv::hide::modal', 'modal-add-edit')
        this.resetModal()
      }
    },
  }
</script>

<style scoped>
  #table-listar {
    margin-top: 16px;
  }
  #per-page-select {
    text-align: left !important;
  }
  #per-page-select .custom-select {
    width: 30% !important;
  }
  #ui-table {
    margin-top: 16px;
  }
  #title-page {
    float: left;
  }
  #btn-cadastrar {
    float: right;
    margin-bottom: 12px;
  }
  .badge-primary {
    margin-right: 8px;
  }
</style>

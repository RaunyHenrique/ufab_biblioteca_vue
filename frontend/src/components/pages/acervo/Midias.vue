<template>

  <div>

    <!--Title-->
    <b-row>

      <b-col>

        <h1 id="title-page" class="text-left">{{title}}</h1>
        <b-button @click.stop="showModal('Cadastrar', null, null, $event.target)" id="btn-cadastrar" variant="primary" style="display: inline-block;">Cadastrar midia</b-button>

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

      <template slot="data" slot-scope="row">{{ row.value | moment("DD/MM/YYYY") }}</template>

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

        <b-form-group label="Tipo de midia"
                      label-for="tipo">
          <b-form-select id="tipo"
                         name="tipo"
                         :options="tipos"
                         required
                         v-model="form.tipo">
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

  import FormModal from "../../layouts/FormModal"

  export default {
    name: "Midias",
    components: { FormModal },
    data () {
      return {
        title: 'Midias',
        items: [],
        url: '/midias',
        method: '',
        modalTitle: '',
        error: false,
        listErros: [],
        modal_form_fields: [
          //OBS: os campos mapeados aqui, são mapeados no form!
          //OBS: os campos que não estão presentes aqui, são os campos personalizados!
          { key: 'id', label: 'Id', type: 'number', hidden: true},
          { key: 'titulo', label: 'Titulo', type: 'text'},
          { key: 'data', label: 'Data de gravação', type: 'date' },
        ],
        table_fields: [
          { key: 'titulo', label: 'Titulo', sortable: true },
          { key: 'data', label: 'Data de gravação', sortable: true },
          { key: 'tipo', label: 'Tipo de midia', sortable: true },
          { key: 'actions', label: 'Ações' }
        ],
        form: {},//todos os campos do form
        currentPage: 1,
        perPage: 5,
        totalRows: 0,
        pageOptions: [ 5, 10, 15 ],
        sortBy: null,
        sortDesc: false,
        sortDirection: 'asc',
        filter: null,
        tipos: [],

        idToDelete: null,
        indexToDelete: null,

        indexToEdit: null,
      }
    },
    created() {
      this.getAllCursos()
      this.getAllTipos()
    },
    methods: {
      getAllCursos() {

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
      getAllTipos() {

        this.$http.get(this.url + '/tipos_de_midias')
          .then(data => {

            if (data.data.length > 0) {
              this.tipos = data.data
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
          campos[key] = null
        })

        this.form = campos

      },
      showModal(title, item, index, event) {

        this.error = false

        if (item == null) {
          this.modalTitle = 'Cadastrar'
          this.form = {}
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

          if (erro['code'] != "NotNull") {

            errs.push(erro['defaultMessage'])

          }

        })

        this.listErros = errs

      },
      resetModal () {
        this.form = {}
      },
      handleSubmit () {
        //fecha modal
        this.$root.$emit('bv::hide::modal', 'modal-add-edit')
        this.resetModal()
      }
    }
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
</style>
